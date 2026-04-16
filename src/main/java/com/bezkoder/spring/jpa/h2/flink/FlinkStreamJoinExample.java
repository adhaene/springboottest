package com.bezkoder.spring.jpa.h2.flink;

import org.apache.flink.api.common.eventtime.SerializableTimestampAssigner;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;

import java.time.Duration;

public class FlinkStreamJoinExample {

    public static void main(String[] args) throws Exception {
        // Set up the execution environment
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // Example input streams (key, value)
        DataStream<Tuple2<String, Integer>> stream1 = env
                .fromElements(
                        Tuple2.of("A", 1),
                        Tuple2.of("B", 2),
                        Tuple2.of("A", 3)
                )
                // Assign timestamps and watermarks
                .assignTimestampsAndWatermarks(
                        WatermarkStrategy.<Tuple2<String, Integer>>forBoundedOutOfOrderness(Duration.ofSeconds(1))
                                .withTimestampAssigner((SerializableTimestampAssigner<Tuple2<String, Integer>>) (element, recordTimestamp) -> System.currentTimeMillis())
                );

        DataStream<Tuple2<String, String>> stream2 = env
                .fromElements(
                        Tuple2.of("A", "apple"),
                        Tuple2.of("B", "banana"),
                        Tuple2.of("C", "cherry")
                )
                .assignTimestampsAndWatermarks(
                        WatermarkStrategy.<Tuple2<String, String>>forBoundedOutOfOrderness(Duration.ofSeconds(1))
                                .withTimestampAssigner((SerializableTimestampAssigner<Tuple2<String, String>>) (element, recordTimestamp) -> System.currentTimeMillis())
                );

        // Perform a windowed join on the key
        DataStream<String> joinedStream = stream1
                .join(stream2)
                .where(t -> t.f0) // key selector for stream1
                .equalTo(t -> t.f0) // key selector for stream2
                .window(TumblingEventTimeWindows.of(Time.seconds(5))) // join window
                .apply((first, second) ->
                        "Key: " + first.f0 +
                                ", Left Value: " + first.f1 +
                                ", Right Value: " + second.f1
                );

        // Output the joined results
        joinedStream.print();

        // Execute the Flink job
        env.execute("Flink DataStream Windowed Join Example");
    }
}
