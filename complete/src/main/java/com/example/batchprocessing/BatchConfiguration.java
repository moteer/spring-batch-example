package com.example.batchprocessing;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.Collections;

@Configuration
public class BatchConfiguration {

    @Autowired
    private DataSource dataSource;


    @Bean
    public ItemReader<Transaction> reader() {
        return new FlatFileItemReaderBuilder<Transaction>()
                .name("TransactionFileReader")
                .linesToSkip(1)
                .resource(new ClassPathResource("transactions.csv"))
                .delimited()
                .names("quellbank", "quellkontonummer", "zielbank", "zielkontonummer", "transaktionsdatum", "betrag")
                .targetType(Transaction.class)
                .customEditors(Collections.singletonMap(LocalDate.class, new CustomLocalDateEditor("yyyy-MM-dd")))
                .build();
    }

    @Bean
    public ItemProcessor<Transaction, Account> processor() {
        return new TransactionItemProcessor();
    }

    @Bean
    public ItemWriter<Account> writer() {
        JdbcBatchItemWriter<Account> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setSql("UPDATE Account SET Saldo = :saldo WHERE Bank = :bank AND Kontonummer = :kontonummer");
        writer.setDataSource(dataSource);
        return writer;
    }

    @Bean
    public Step processTransactionsStep(ItemReader<Transaction> reader,
                                        ItemProcessor<Transaction, Account> processor,
                                        ItemWriter<Account> writer,
                                        JobRepository jobRepository,
                                        PlatformTransactionManager transactionManager) {

        return new StepBuilder("processTransactionsStep", jobRepository)
                .<Transaction, Account>chunk(10, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public Job processTransactionJob(JobRepository jobRepository,
                                     Step processTransactionsStep,
                                     JobCompletionNotificationListener listener) {
        return new JobBuilder("processTransactionJob", jobRepository)
                .listener(listener)
                .start(processTransactionsStep)
                .build();
    }
}
