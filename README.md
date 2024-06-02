### **Instana Distributed Tracing**

The progect is to find out if there are any microservices running on the
server. If it finds such a microservice, it will start instrumenting that service and it starts tracking
key performance indicators (KPI’s) of the HTTP connections between this service and its
neighboring services by looking at the HTTP library calls that service is performing. One of
these KPI’s the agent tracks is latency. Please note that latency is a uni-directional concept.
When the latency of service A calling service B is 8ms, it is not implied that service B calling
service A will also take 8ms as this depends on the underlying network infrastructure.

#### **Solution**

using the Depth-first Search (DFS) to solve the exercise with Guava Value Graph (Google core library) that provides suitable methods for
the implementation.
