#add required imports
from concurrent import futures
from jproperties import Properties
from zeroconf import IPVersion, ServiceInfo, Zeroconf

import logging
import grpc
import fire_pb2
import fire_pb2_grpc
import socket
import queue
import jpysocket

#create the fire class
class fire (fire_pb2_grpc.fireServicer):
    #create empty array called temp
    temp = []
    #create contactFire function
    def contactFire(self, request, context):
        temp = request.text
        return fire_pb2.fireResponse(value="Received -%s"%temp+"- from client")
    #Create emergencyFire function
    def emergencyFire(self, request, context):
        self.temp
        temp = request.name
        temp1 = request.injury
        temp2 = request.address
        temp3 = request.priority
        self.temp.append(temp)
        for t in self.temp:
            yield fire_pb2.fireResponseStream(text="Received name - %s"%temp+" Received injury - %s"%temp1+" Received address - %s"%temp2+" Received priority - %s"%temp3)

         
#create serve function  to start the server 
def serve():
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
    fire_pb2_grpc.add_fireServicer_to_server(fire(), server)
    server.add_insecure_port('[::]:50052')
    server.start()
    print('Server started...')
    server.wait_for_termination()

#create register function to register the server using the properties
def register():
    global zeroconf
    desc = {'path': 'fire.properties'}
    
    info = ServiceInfo(
        "_http._tcp.local.",
        "fire._http._tcp.local.",
        addresses=[socket.inet_aton("127.0.0.1")],
        port=50052,
        properties=desc,
        server="fire.local.",
    )
    zeroconf = Zeroconf()
    zeroconf.register_service(info)
    print('registered...')

#create getProp function to get the properties of the server
def getProp():
    configs = Properties()
    with open('fire.properties', 'rb') as config_file:
        configs.load(config_file)
    print('Service properties')
    print(configs.get("service_type"))
    print(configs.get("service_name"))
    print(configs.get("service_description"))
    print(configs.get("service_port"))

#Main method use to call the other functions on the python script
if __name__ == "__main__":
    logging.basicConfig()
    getProp()
    register()
    serve()
