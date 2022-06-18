var service = require('../proto/Student_grpc_pb');
var message = require('../proto/Student_pb');

var grpc = require('grpc')

var server = new grpc.Server();

server.addService(service.StudentServiceService, {
    getRealNameByUsername: getRealNameByUsername
});

server.bind('localhost:8899', grpc.ServerCredentials.createInsecure());
server.start();

function getRealNameByUsername(call, callback) {
    console.log('request: ' + call.request.getUsername());

    var res = new message.MyResponse();
    res.setRealname('zhao liu');

    callback(null, res);
}