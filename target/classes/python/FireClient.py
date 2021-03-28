#add required inputs for the client application
import logging
import grpc
import fire_pb2_grpc
import fire_pb2
#Create new function for sending a request to the first service on the server
def contact():
    with grpc.insecure_channel('localhost:50052') as channel:
        stub = fire_pb2_grpc.fireStub(channel)
        response = stub.contactFire(fire_pb2.contactFireRequest(text = 'Fire emergency service required'))
    print("Server responded: "+response.value)
    
#Create new function to send a request to the second service
def emergency():
    with grpc.insecure_channel('localhost:50052') as channel:
        stub = fire_pb2_grpc.fireStub(channel)
        response = stub.emergencyFire(fire_pb2.fireEmergency(name = 'Conor', injury = 'None', address = '16 Greenwood', priority = 'Low'))
        for resp in response:
            print('Server responded: %s' %resp)
#Main method calls the other two functions
if __name__ == "__main__":
    logging.basicConfig()
    contact()
    emergency()