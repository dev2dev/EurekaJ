#!/bin/bash

mkdir eurekajproxy
cd eurekajproxy
curl -o eurekaJ.Proxy.jar http://nightly.haagen.name/EurekaJ/latest/eurekaJ.Proxy-1.0.RC1-jar-with-dependencies.jar
curl -o config.properties https://raw.github.com/joachimhs/EurekaJ/master/EurekaJ.Proxy/config.properties
curl -o run.sh https://raw.github.com/joachimhs/EurekaJ/master/EurekaJ.Proxy/run..sh
chmod 755 run.sh
