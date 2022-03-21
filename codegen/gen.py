#!/usr/bin/python3
import os

from typing import List
from Entity import Entity
from Field import Field
from Controller import Controller
from ModelAssembler import ModelAssembler
from NotFoundAdvice import NotFoundAdvice
from NotFoundException import NotFoundException
from Repository import Repository
from Service import Service
from ServiceImpl import ServiceImpl


def test():
    dir = os.getcwd() + '/../src/main/java/com/cinapse/javastudy'
    package = 'package com.cinapse.javastudy;'
    className = 'Vlan'
    varName = 'vlan'
    pluralName = 'vlans'
    fieldList = [
        Field('String', 'mgmtIp'),
        Field('String', 'vlans'),
    ]

    entity = Entity(package, className, varName, fieldList)
    print(entity)

    controller = Controller(package, className, pluralName)
    print(controller)

    assem = ModelAssembler(package, className, varName, pluralName)
    print(assem)

    advice = NotFoundAdvice(package, className)
    print(advice)

    exception = NotFoundException(package, className, varName)
    print(exception)

    repo = Repository(package, className)
    print(repo)

    service = Service(package, className, varName)
    print(service)

    impl = ServiceImpl(package, className, varName, pluralName, fieldList)
    print(impl)

    save(dir, className + '.java', str(entity))
    save(dir, className + 'Controller.java', str(controller))
    save(dir, className + 'ModelAssembler.java', str(assem))
    save(dir, className + 'NotFoundAdvice.java', str(advice))
    save(dir, className + 'NotFoundException.java', str(exception))
    save(dir, className + 'Repository.java', str(repo))
    save(dir, className + 'Service.java', str(service))
    save(dir, className + 'ServiceImpl.java', str(impl))


def save(dir: str, filename: str,  content: str):
    f = open(dir + '/' + filename, 'w')
    f.write(content)
    f.close()


test()
