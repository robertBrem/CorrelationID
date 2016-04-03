# CorrelationID
```
curl -i http://localhost:8080/correlationid/resources/orders
```
```
[2016-04-03T12:38:30.261+0200] [Payara 4.1] [INFO] [] [] [tid: _ThreadID=30 _ThreadName=http-listener-1(1)] [timeMillis: 1459679910261] [levelValue: 800] [[
  request: http://localhost:8080/correlationid/resources/orders-http-listener-1(1)-1459679910261]]

[2016-04-03T12:38:30.314+0200] [Payara 4.1] [INFO] [] [] [tid: _ThreadID=85 _ThreadName=http-listener-1(3)] [timeMillis: 1459679910314] [levelValue: 800] [[
  request: http://localhost:8080/correlationid/resources/orders-http-listener-1(1)-1459679910261]]

[2016-04-03T12:38:30.316+0200] [Payara 4.1] [INFO] [] [] [tid: _ThreadID=85 _ThreadName=http-listener-1(3)] [timeMillis: 1459679910316] [levelValue: 800] [[
  response: http://localhost:8080/correlationid/resources/orders-http-listener-1(1)-1459679910261]]

[2016-04-03T12:38:30.324+0200] [Payara 4.1] [INFO] [] [] [tid: _ThreadID=129 _ThreadName=Thread-29] [timeMillis: 1459679910324] [levelValue: 800] [[
  response: http://localhost:8080/correlationid/resources/orders-http-listener-1(1)-1459679910261]]
```
