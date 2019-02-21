# Part-A-Mailbot-Blues

You, an independent Software Contractor, have been hired by XYZ Company to provide some
much needed assistance in delivering the latest version of their product Automail to market. The Automail is
an automated mail sorting and delivery system designed to operate in large buildings that have dedicated
Mail rooms. It offers end to end receipt and delivery of all mail within the large building, and can be tweaked
to fit many different installation environments. The system consists of four key components:
* A Mail Pool system which can hold a number of mail items which have arrived at the building.
* A Delivery Robot (see Figure 1) which delivers mail items from the mail pool throughout the building.
* A Storage Unit, that is, a ‘backpack’ which is attached to the delivery robot. It can contain at most
four mail items; these mail items are in delivery order.
* A Mail Selecting system which decides what mail items should go into a robot’s backpack for delivery
and in what order, and in some cases which items should come out.

The hardware of this system has been well tested, and the system engineers have confidence in their design.
Unfortunately, the performance seen so far has been less than optimal. XYZ Company has
traditionally been a hardware company, and as such do not have much software development experience. As a
result, the strategies that they are using to organise the mail and select the mail for delivery are very poor.
Your job is to apply your software engineering knowledge to fix the mail sorting and to develop a better
strategy for selecting the mail for delivery, all with the aim to improve the performance of their system. Once
you have made your changes, they will be benchmarked to provide feedback on your performance to XYZ Company.
