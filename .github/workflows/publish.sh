#!/usr/bin/env bash
cd client
sed -i "s/gametrader-user-service-rest-client/gametrader-user-service-rest-client$1/" settings.gradle
./gradlew publish