package com.iinux.untitledgradle.protostudy;

import com.iinux.untitledgradle.proto.*;
import io.grpc.stub.StreamObserver;

public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase {
    @Override
    public void getRealNameByUsername(MyRequest request, StreamObserver<MyResponse> responseObserver) {
        // 没有注释这一行，客户端会认为服务端没有实现，然后关闭连接，服务端会报 call is closed
        // super.getRealNameByUsername(request, responseObserver);

        System.out.println("accept client " + request.getUsername());

        MyResponse response = MyResponse.newBuilder().setRealname("张三").build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getStudentsByAge(StudentRequest request, StreamObserver<StudentResponse> responseObserver) {
        System.out.println("accept client " + request.getAge());

        responseObserver.onNext(StudentResponse.newBuilder().setName("张三").setAge(20).setCity("北京").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("张三2").setAge(30).setCity("北京2").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("张三3").setAge(40).setCity("北京3").build());

        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<StudentRequest> getStudentsWrapperByAges(StreamObserver<StudentResponseList> responseObserver) {
        return new StreamObserver<StudentRequest>() {
            @Override
            public void onNext(StudentRequest value) {
                System.out.println(value.getAge());
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onCompleted() {
                StudentResponse s1 = StudentResponse.newBuilder().setName("张三").setAge(20).setCity("北京").build();
                StudentResponse s2 = StudentResponse.newBuilder().setName("张三2").setAge(30).setCity("北京2").build();
                responseObserver.onNext(StudentResponseList.newBuilder().addStudentResponse(s1).addStudentResponse(s2)
                        .build());
                responseObserver.onCompleted();
            }
        };
    }
}
