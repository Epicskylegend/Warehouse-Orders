import java.util.Scanner;
import java.util.Stack;

class orders {

    public static void main(String[] args) {
        Scanner orders = new Scanner(System.in);

        Stack <Integer> ordersToBeFilled = new Stack<>(); // Stack for customer orders
        Warehouse stock = new Warehouse(); // Warehouse object that gives us access to variables inside the warehouse class.
        Stack <Integer> widgetsOnHand = new Stack<>(); // Stack that keeps track of widgets we have in stock.



        System.out.println("How many shipments of widgets were received?"); // Prompts user to say how many shipments were received.


        int newShipments = orders.nextInt(); // If input is not a number an exception should be thrown here

        System.out.println("We received: " + newShipments + " new shipments."); // Displays the amount of widget shipments received

        System.out.println("How many widgets were in those shipments?"); // If input is not a number an exception should be thrown here


        int totalNumWidgets = 0; // For loops that adds together the amount of widgets in each shipment and stores them in a variable.
        for (int i = 1; i <= newShipments; i++) {
            int widgetsPerShipment = orders.nextInt(); // If input is not a number an exception should be thrown here

            System.out.println("Order " + i +  " had " + widgetsPerShipment + ".");

            totalNumWidgets += widgetsPerShipment;
        }
        System.out.println("We have a total of " + totalNumWidgets + " widgets in the warehouse.\n");




        System.out.println("How many orders are there for widgets?");

        int widgetOrders = orders.nextInt(); // If input is not a number an exception should be thrown here

        System.out.println("We received: " + widgetOrders + " widget order(s).");
        System.out.println("How many widgets were requested in each of those orders?");

        int widgetsPerOrder; // If input is not a number an exception should be thrown here

        for (int i = 1; i <= widgetOrders; i++) { // Keeps track of how many widgets orders there are
            widgetsPerOrder = orders.nextInt(); // If input is not a number an exception should be thrown here

            System.out.println("Order " + i + " had " + widgetsPerOrder + " widgets.\n");
            ordersToBeFilled.push(widgetsPerOrder); // "OrderToBeFilled" stack gets updated with elements equalling the amount of orders there are with the amount of widgets in each order as the stored values
        }

        for (int i = 1; i <= totalNumWidgets; i++ ) {
            widgetsOnHand.push(i);
        }

        System.out.println("We'll start processing orders starting with the next most recent order of " + ordersToBeFilled.peek() + " widget(s).\n");
        System.out.println("Widgets for these orders will be billed at $" + stock.widgetOrderPrice + " each.\n");



        for(int x = widgetOrders; x>=0;x--) { // Loops to iterate through each order starting with the most recent
                for (int i = 1; i <= ordersToBeFilled.peek(); i++) { // Removes elements on in the "widgetsOnHand" stack based on the amount in each order if there is enough stock one at a time.
                    if (totalNumWidgets >= ordersToBeFilled.peek()) { // If stack is empty an exception should be thrown
                        widgetsOnHand.pop();

                    }
                    else if(totalNumWidgets < ordersToBeFilled.peek()) { // If there isn't enough stock display a message that tells us this information. If stack is empty an exception should be thrown
                        System.out.println("We don't have enough stock to fill order " + x + " which contains " + ordersToBeFilled.peek() + " widgets. We'll process this order once we have enough widgets in stock.\n");
                        return;

                    }

                }

                    totalNumWidgets -= ordersToBeFilled.peek(); // Subtracts the amount of widgets we have in stock by the amount in each fulfilled order.

                    double orderPrice = stock.widgetOrderPrice * ordersToBeFilled.peek();
                    // Code below will display the order number, widgets per order as well as the cost for each fulfilled order. //
                    System.out.println("This makes the total cost of order #" + x + " which contains " + ordersToBeFilled.pop()  + " widget(s) $" + orderPrice + ".\n");
                    System.out.println("-----------------------------------------------------");
                    System.out.println("We currently have " + widgetsOnHand.peek() + " widgets left in stock.\n");

                    if(ordersToBeFilled.isEmpty()) {
                        System.out.println("We've successfully fulfilled all outstanding orders.\n");
                    }
            }
    }
}
