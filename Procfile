worker: java -Xmx300m \
             -Xss512k \
             -XX:CICompilerCount=2 \
             -XX:+PrintGCDetails \
             -XX:+PrintGCDateStamps \
             -XX:+PrintTenuringDistribution \
             -XX:+UseConcMarkSweepGC \
             -javaagent:/app/newrelic/newrelic.jar \
             -jar target/Bot-1.0-uber.jar