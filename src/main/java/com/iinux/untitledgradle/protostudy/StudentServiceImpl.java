package com.iinux.untitledgradle.protostudy;

import com.iinux.untitledgradle.proto.MyRequest;
import com.iinux.untitledgradle.proto.MyResponse;
import com.iinux.untitledgradle.proto.StudentServiceGrpc;
import io.grpc.stub.StreamObserver;

public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase {
    @Override
    public void getRealNameByUsername(MyRequest request, StreamObserver<MyResponse> responseObserver) {
        super.getRealNameByUsername(request, responseObserver);

        System.out.println("accept client " + request.getUsername());

        responseObserver.onNext(MyResponse.newBuilder().setRealname("张三").build());
        responseObserver.onCompleted();
    }
}
