package com.iinux.untitledgradle.protostudy;

import com.iinux.untitledgradle.proto.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Iterator;

public class GrpcClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8899)
                .usePlaintext(true).build();
        StudentServiceGrpc.StudentServiceBlockingStub stub = StudentServiceGrpc.newBlockingStub(channel);
        MyResponse response = stub.getRealNameByUsername(MyRequest.newBuilder().setUsername("zhang san").build());
        System.out.println(response.getRealname());

        Iterator<StudentResponse> iter = stub.getStudentsByAge(StudentRequest.newBuilder().setAge(20).build());
        while (iter.hasNext()) {
            StudentResponse res = iter.next();
            System.out.println(res.getName() + "," + res.getAge() + "," + res.getCity());
        }
    }
}
