package com.iinux.untitledgradle.protostudy;

import com.iinux.untitledgradle.proto.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.time.LocalDateTime;
import java.util.Iterator;

public class GrpcClient {
    public static void main(String[] args) throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8899)
                .usePlaintext(true).build();
        StudentServiceGrpc.StudentServiceBlockingStub stub = StudentServiceGrpc.newBlockingStub(channel);
        StudentServiceGrpc.StudentServiceStub stub1 = StudentServiceGrpc.newStub(channel);
        MyResponse response = stub.getRealNameByUsername(MyRequest.newBuilder().setUsername("zhang san").build());
        System.out.println(response.getRealname());

        Iterator<StudentResponse> iter = stub.getStudentsByAge(StudentRequest.newBuilder().setAge(20).build());
        while (iter.hasNext()) {
            StudentResponse res = iter.next();
            System.out.println(res.getName() + "," + res.getAge() + "," + res.getCity());
        }

        StreamObserver<StudentRequest> res = stub1.getStudentsWrapperByAges(new StreamObserver<StudentResponseList>() {
            @Override
            public void onNext(StudentResponseList value) {
                value.getStudentResponseList().forEach(res -> {
                    System.out.println(res.getName() + "," + res.getAge() + "," + res.getCity());
                });
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onCompleted() {
                System.out.println("on completed");
            }
        });

        res.onNext(StudentRequest.newBuilder().setAge(20).build());
        res.onNext(StudentRequest.newBuilder().setAge(30).build());
        res.onNext(StudentRequest.newBuilder().setAge(40).build());

        res.onCompleted();

        StreamObserver<StreamRequest> res1 = stub1.biTalk(new StreamObserver<StreamResponse>() {
            @Override
            public void onNext(StreamResponse value) {
                System.out.println(value.getResponseInfo());
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onCompleted() {
                System.out.println("on completed");
            }
        });

        for (int i = 0; i < 10; i++) {
           res1.onNext(StreamRequest.newBuilder().setRequestInfo(LocalDateTime.now().toString()).build());

           Thread.sleep(1000);
        }

        Thread.sleep(Integer.MAX_VALUE);
    }
}
