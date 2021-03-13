#!/bin/sh

exec java ${JAVA_OPTS} -Duser.timezone=UTC -XX:+AlwaysPreTouch -XX:+DisableExplicitGC -XX:-UseBiasedLocking -XX:MaxRAMPercentage=80 -XX:+UseContainerSupport -Djava.security.egd=file:/dev/./urandom -cp /app/resources/:/app/classes/:/app/libs/* "com.example.demo.DemoApplication"  "$@"
