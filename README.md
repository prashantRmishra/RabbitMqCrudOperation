# RabbitMqCrudOperation
In this Application I have Implemented Message queue operation using popular RabbitMq in java <br>
[More information about rabbitMq](https://www.rabbitmq.com/getstarted.html) <br>

The main benefits of message queueing: resilience. <br>

Imagine this: you need to communicate with two or three other systems. A common approach these days will be web services which is fine if you need an answers right away. <br>

However: web services can be down and not available - what do you do then? Putting your message into a message queue (which has a component on your machine/server, too) typically will work in this scenario - your message just doesn't get delivered and thus processed right now - but it will later on, when the other side of the service comes back online. <br>

So in many cases, using message queues to connect disparate systems is a more reliable, more robust way of sending messages back and forth. It doesn't work well for everything (if you want to know the current stock price for MSFT, putting that request into a queue might not be the best of ideas) - but in lots of cases, like putting an order into your supplier's message queue, it works really well and can help ease some of the reliability issues with other technologies.

