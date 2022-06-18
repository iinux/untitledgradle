var server = require('../proto/Student_grpc_pb');
var message = require('../proto/Student_pb');

var grpc = require('grpc');

var client = new server.StudentServiceClient('localhost:8899', grpc.credentials.createInsecure());

var request = new message.MyRequest();
request.setUsername('zhangsan');

client.getRealNameByUsername(request, function (error, respData) {
    console.log(respData.getRealname());
});