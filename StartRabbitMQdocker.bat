@echo off
echo "Did you turn on Docker? DO IT!!!!!"
docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.12-management && sleep 10 && rabbitmq-plugins enable rabbitmq_web_stomp