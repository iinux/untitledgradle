var PROTO_FILE_PATH = '../proto/Student.proto';
var grpc = require('grpc');
var grpcService = grpc.load(PROTO_FILE_PATH).com.iinux.untitledgradle.proto;

var server = new grpc.Server();

server.addService(grpcService.StudentService.service, {
    getRealNameByUsername: getRealNameByUsername
});

server.bind('localhost:8899', grpc.ServerCredentials.createInsecure());
server.start();

function getRealNameByUsername(call, callback) {
    console.log(call);
    callback(null, {realname: 'zhangsan'});
}