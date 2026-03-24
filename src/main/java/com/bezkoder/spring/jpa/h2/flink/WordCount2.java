package com.bezkoder.spring.jpa.h2.flink;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.util.Collector;

public class WordCount2 {
    public static void main(String[] args) throws Exception {
        // Set up the execution environment
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        // Input data: a collection of text lines
        DataSet<String> text = env.fromElements(
                "Apache Flink is a powerful framework",
                "Flink processes data efficiently",
                "Flink is scalable and fast"
        );
        // Transform the input data
        DataSet<Tuple2<String, Integer>> wordCounts = text
                .flatMap(new LineSplitter()) // Split lines into words
                .groupBy(0) // Group by the word (field 0)
                .sum(1); // Sum the counts (field 1)
        // Print the result
        wordCounts.print();
    }
    // Helper class to split lines into words
    public static final class LineSplitter implements FlatMapFunction<String, Tuple2<String, Integer>> {
        @Override
        public void flatMap(String value, Collector<Tuple2<String, Integer>> out) {
            // Normalize and split the line into words
            String[] tokens = value.toLowerCase().split("\\W+");
            for (String token : tokens) {
                if (token.length() > 0) {
                    out.collect(new Tuple2<>(token, 1)); // Emit (word, 1)
                }
            }
        }
    }
}