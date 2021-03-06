# Generated by the gRPC Python protocol compiler plugin. DO NOT EDIT!
"""Client and server classes corresponding to protobuf-defined services."""
import grpc

import fire_pb2 as fire__pb2


class fireStub(object):
    """Missing associated documentation comment in .proto file."""

    def __init__(self, channel):
        """Constructor.

        Args:
            channel: A grpc.Channel.
        """
        self.contactFire = channel.unary_unary(
                '/fire.fire/contactFire',
                request_serializer=fire__pb2.contactFireRequest.SerializeToString,
                response_deserializer=fire__pb2.fireResponse.FromString,
                )
        self.emergencyFire = channel.unary_stream(
                '/fire.fire/emergencyFire',
                request_serializer=fire__pb2.fireEmergency.SerializeToString,
                response_deserializer=fire__pb2.fireResponseStream.FromString,
                )


class fireServicer(object):
    """Missing associated documentation comment in .proto file."""

    def contactFire(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')

    def emergencyFire(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')


def add_fireServicer_to_server(servicer, server):
    rpc_method_handlers = {
            'contactFire': grpc.unary_unary_rpc_method_handler(
                    servicer.contactFire,
                    request_deserializer=fire__pb2.contactFireRequest.FromString,
                    response_serializer=fire__pb2.fireResponse.SerializeToString,
            ),
            'emergencyFire': grpc.unary_stream_rpc_method_handler(
                    servicer.emergencyFire,
                    request_deserializer=fire__pb2.fireEmergency.FromString,
                    response_serializer=fire__pb2.fireResponseStream.SerializeToString,
            ),
    }
    generic_handler = grpc.method_handlers_generic_handler(
            'fire.fire', rpc_method_handlers)
    server.add_generic_rpc_handlers((generic_handler,))


 # This class is part of an EXPERIMENTAL API.
class fire(object):
    """Missing associated documentation comment in .proto file."""

    @staticmethod
    def contactFire(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/fire.fire/contactFire',
            fire__pb2.contactFireRequest.SerializeToString,
            fire__pb2.fireResponse.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)

    @staticmethod
    def emergencyFire(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_stream(request, target, '/fire.fire/emergencyFire',
            fire__pb2.fireEmergency.SerializeToString,
            fire__pb2.fireResponseStream.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)
