from __future__ import print_function

import socket

"""Basic UDP server found on python wiki: https://wiki.python.org/moin/UdpCommunication#Receiving"""

def main():

    UDP_IP = "127.0.0.1"
    UDP_PORT = 5005

    sock = socket.socket(socket.AF_INET, # Internet
                         socket.SOCK_DGRAM) # UDP
    sock.bind((UDP_IP, UDP_PORT))

    print('UDP server running at IP {0} at PORT {1}'.format(UDP_IP, UDP_PORT))
    print('Listening for data...')
    # noinspection PyInterpreter
    while True:
        data, addr = sock.recvfrom(1024) # buffer size is 1024 bytes
        print('received message:', data)
        if isMessageValid(data):
            sendOkSignal(sock, addr)
        if data == b'KILL':
            break


def isMessageValid(message):
    valid_message = [
        b'command',
        b'left',
        b'right',
        b'KILL',
    ]

    if message in valid_message:
        return True
    return False


def sendOkSignal(socket, ip_and_port):
    print('Sending "ok" message to {0}'.format(ip_and_port))
    socket.sendto(b'ok', ip_and_port)


if __name__ == '__main__':
    main()