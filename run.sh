#!/usr/bin/env sh

docker run -it --rm -v $(pwd):/app -v ~/Library/Caches/Coursier:/root/.cache/coursier/ -p 8080:8080 hseeberger/scala-sbt:8u252_1.3.10_2.13.2 bash -c "cd /app && sbt run"
