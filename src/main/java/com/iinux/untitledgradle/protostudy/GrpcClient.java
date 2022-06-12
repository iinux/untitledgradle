package com.iinux.untitledgradle.protostudy;

import com.iinux.untitledgradle.proto.MyRequest;
import com.iinux.untitledgradle.proto.MyResponse;
import com.iinux.untitledgradle.proto.StudentServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8899)
                .usePlaintext(true).build();
        StudentServiceGrpc.StudentServiceBlockingStub stub = StudentServiceGrpc.newBlockingStub(channel);
        MyResponse response = stub.getRealNameByUsername(MyRequest.newBuilder().setUsername("zhang san").build());

        System.out.println(response.getRealname());
    }
}
