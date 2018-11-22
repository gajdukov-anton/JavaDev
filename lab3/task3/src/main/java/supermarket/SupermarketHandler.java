package supermarket;

import com.company.supermarket.cashDesk.bill.Bill;
import com.company.supermarket.cashDesk.CashDesk;
import com.company.supermarket.customer.Customer;
import com.company.supermarket.customer.CustomerHandler;
import com.company.supermarket.product.discount.Discount;
import com.company.supermarket.product.Product;
import com.company.supermarket.info.message.Message;
import com.company.supermarket.info.report.Report;
import com.company.supermarket.time.Time;
import javafx.util.Pair;

import java.util.*;

public class SupermarketHandler {
    private Supermarket supermarket;
    private List<String> names;
    private List<String> lastNames;
    private List<String> statusList;
    private HashMap<String, Boolean> productAccess;
    private HashMap<String, Boolean> productCountable;
    private int idCustomer = 0;
    private Report report;

    public SupermarketHandler(Supermarket supermarket) {
        this.supermarket = supermarket;
        this.productAccess = new HashMap<>();
        this.names = new ArrayList<>();
        this.lastNames = new ArrayList<>();
        this.statusList = new ArrayList<>();
        this.productCountable = new HashMap<>();
        fillLastNamesList();
        fillNamesList();
        fillStatusList();
    }

    public void openSupermarket(Time time) {
        if (supermarket.canBeOpen(time)) {
            supermarket.open(createProducts());
            report = new Report();
            Message.createMessageForOpen();
        }
    }

    public void closeSupermarket(Time time) {
        if (supermarket.canBeClose(time)) {
            supermarket.getCashDesk().closeCashDesk(new CashDesk.CloseListener() {
                @Override
                public void onCloseCashDesk(List<Customer> customers) {
                    supermarket.getCustomers().addAll(customers);
                }
            });
            int amountCustomers = supermarket.getCustomers().size();
            for (int i = 0; i < amountCustomers; i++) {
                removeCustomerFromSupermarket(supermarket.getCustomers().get(0));
            }
            supermarket.close();
            report.showReport();
            report = null;
            Message.createMessageForClose();
        }
    }

    public List<Pair<Product, Integer>> createProducts() {
        List<Pair<Product, Integer>> products = new ArrayList<>();
        fillProductAccess();
        fillProductCountable();
        for (HashMap.Entry<String, Boolean> elem : productAccess.entrySet()) {
            Product product = new Product(elem.getKey(), createPrice(), elem.getValue());
            product.setCountable(productCountable.get(product.getTitle()));
            setDiscountForProduct(product);
            products.add(new Pair<>(product, createAmount()));
        }
        return products;
    }


    private void setDiscountForProduct(Product product) {
        Random random = new Random();
        if (random.nextInt(3) == 0) {
            Discount discount = new Discount();
            product.setDiscount(discount);
            Message.createMessageForSetDiscount(discount, product);
        }
    }


    public void addNewCustomerInSupermarket() {
        Customer customer = new Customer(idCustomer);
        customer.setName(createName());
        customer.setLastName(createLastName());
        customer.setStatus(createStatus());
        if (supermarket.isOpen()) {
            supermarket.getCustomers().add(customer);
            this.idCustomer++;
            report.increaseCustomers();
            Message.createMessageForAddNewCustomer(customer);
        } else {
            System.out.println("Supermarket is not open");
        }
    }

    public void removeCustomerFromSupermarket() {
        if (!supermarket.getCustomers().isEmpty()) {
            Customer customer = getCustomerFromSupermarket();
            if (!customer.getBasket().getProducts().isEmpty()) {
                CustomerHandler customerHandler = new CustomerHandler(customer);
                customerHandler.returnAllProductFromBasket(new CustomerHandler.RemoveProductListener() {
                    @Override
                    public void onRemoveProductFromBusker(Product product, int amount) {
                        returnProductToSupermarket(product, amount);
                    }
                });
            }
            supermarket.getCustomers().remove(customer);
            Message.createMessageForRemoveCustomer(customer.getName(), customer.getLastName(), supermarket.getCustomers().size());
        }
    }

    private void removeCustomerFromSupermarket(Customer customer) {
        if (!supermarket.getCustomers().isEmpty()) {
            if (!customer.getBasket().getProducts().isEmpty()) {
                CustomerHandler customerHandler = new CustomerHandler(customer);
                customerHandler.returnAllProductFromBasket(new CustomerHandler.RemoveProductListener() {
                    @Override
                    public void onRemoveProductFromBusker(Product product, int amount) {
                        returnProductToSupermarket(product, amount);
                    }
                });
            }
            supermarket.getCustomers().remove(customer);
            Message.createMessageForRemoveCustomer(customer.getName(), customer.getLastName(), supermarket.getCustomers().size());
        }
    }

    private void returnProductToSupermarket(Product product, int amount) {
        int id = supermarket.getIdProduct(product);
        if (id >= 0) {
            int amountProductInSupermarket = supermarket.getProducts().get(id).getValue();
            supermarket.getProducts().set(id, new Pair<>(product, amountProductInSupermarket + amount));
        }
    }

    // TODO: 19.11.2018 убрать дублирование кода
    public void addProductToBasket() {
        if (!supermarket.getCustomers().isEmpty() && !supermarket.getProducts().isEmpty()) {
//            int id = getIdProductFromSupermarket();
//            Product product = supermarket.getProducts().get(id).getKey();
//            if (supermarket.getProducts().get(id).getValue() > 0) {
//                CustomerHandler customerHandler = new CustomerHandler(getCustomerFromSupermarket());
//                customerHandler.addProductToBasket(product, createAmountProductsForCustomer(product), new CustomerHandler.AddProductListener() {
//                    @Override
//                    public void onAddProductToBasket(Product product, int amount) {
//                        int amountProduct = supermarket.getProducts().get(id).getValue();
//                        supermarket.getProducts().set(id, new Pair<>(product, amountProduct - amount));
//                    }
//                });
//            } else {
//                Message.createMessageForNoProductInSupermarket(product, getCustomerFromSupermarket());
//            }
            addProduct(getCustomerFromSupermarket());
        }
    }

    // TODO: 19.11.2018 убрать дублирование кода
    private void addProductToBasket(Customer customer) {
        if (!supermarket.getProducts().isEmpty()) {
//            int id = getIdProductFromSupermarket();
//            Product product = supermarket.getProducts().get(id).getKey();
//            CustomerHandler customerHandler = new CustomerHandler(customer);
//            customerHandler.addProductToBasket(product, createAmountProductsForCustomer(product), new CustomerHandler.AddProductListener() {
//                @Override
//                public void onAddProductToBasket(Product product, int amount) {
//                    int amountProduct = supermarket.getProducts().get(id).getValue();
//                    supermarket.getProducts().set(id, new Pair<>(product, amountProduct - amount));
//                }
//            });
            addProduct(customer);
        }
    }

    private void addProduct(Customer customer) {
        int id = getIdProductFromSupermarket();
        Product product = supermarket.getProducts().get(id).getKey();
        if (supermarket.getProducts().get(id).getValue() > 0) {
            CustomerHandler customerHandler = new CustomerHandler(customer);
            customerHandler.addProductToBasket(product, createAmountProductsForCustomer(product), new CustomerHandler.AddProductListener() {
                @Override
                public void onAddProductToBasket(Product product, int amount) {
                    int amountProduct = supermarket.getProducts().get(id).getValue();
                    supermarket.getProducts().set(id, new Pair<>(product, amountProduct - amount));
                }
            });
        } else {
            Message.createMessageForNoProductInSupermarket(product, getCustomerFromSupermarket());
        }
    }

    public void setCustomerInCashDesk() {
        if (!supermarket.getCustomers().isEmpty()) {
            Customer customer = getCustomerFromSupermarket();
            if (customer.getBasket().getProducts().isEmpty()) {
                addProductToBasket(customer);
            } else {
                supermarket.getCashDesk().addCustomer(customer);
                supermarket.getCustomers().remove(customer);
            }
        }
    }

    public void updateCashDesk() {
        if (supermarket.isOpen()) {
            supermarket.getCashDesk().updateQueue(new CashDesk.Callback() {
                @Override
                public void onUserWentOutFromCashDesk(Customer customer) {
                    supermarket.getCustomers().add(customer);
                    removeCustomerFromSupermarket(customer);
                }

                @Override
                public void onCustomerGetProhibitedProduct(Customer customer, List<Pair<Product, Integer>> prohibitedProducts) {
                    int amount = prohibitedProducts.size();
                    for (int i = 0; i < amount; i++) {
                        System.out.println("lol");
                        returnProductFromBasket(customer, prohibitedProducts.get(i).getKey(), prohibitedProducts.get(i).getValue());
                    }
                }

                @Override
                public void onCashDeskCreateBill(Customer customer, Bill bill) {
                    CustomerHandler customerHandler = new CustomerHandler(customer);
                    for (int i = 0; i < bill.getProducts().size(); i++) {
                        report.addProduct(bill.getProducts().get(i).getKey(), bill.getProducts().get(i).getValue());
                    }
                    customerHandler.payForProducts(bill);
                }
            });
        }
    }

    public void returnProductFromBasket(Customer customer, Product product, int amount) {
        CustomerHandler customerHandler = new CustomerHandler(customer);
        customerHandler.returnProductFromBasket(new Pair<>(product, amount), new CustomerHandler.RemoveProductListener() {
            @Override
            public void onRemoveProductFromBusker(Product product, int amount) {
                returnProductToSupermarket(product, amount);
            }
        });
    }

    private int createAmountProductsForCustomer(Product product) {
        if (!product.isCountable()) {
            int id = supermarket.getIdProduct(product);
            int amountProductInSupermarket = supermarket.getProducts().get(id).getValue();
            Random random = new Random();
            int amountProductForCustomer = 1 + random.nextInt(4);
            if (amountProductForCustomer > amountProductInSupermarket) {
                return amountProductInSupermarket;
            } else {
                return amountProductForCustomer;
            }
        } else {
            return 1;
        }
    }

    public void doAction() {
        if (supermarket.isOpen()) {
            int numberOfAction = getNumberOfAction(5);
            if (numberOfAction == 0) {
                addNewCustomerInSupermarket();
            }
            if (numberOfAction == 1) {
                removeCustomerFromSupermarket();
            }
            if (numberOfAction == 2) {
                addProductToBasket();
            }
            if (numberOfAction == 3) {
                setCustomerInCashDesk();
            }
        }
    }


    private int getNumberOfAction(int amount) {
        if (amount > 1) {
            Random random = new Random();
            return random.nextInt(amount);
        } else {
            return 0;
        }
    }

    private int getIdProductFromSupermarket() {
        if (this.supermarket.getProducts().size() > 1) {
            Random random = new Random();
            return random.nextInt(this.supermarket.getProducts().size() - 1);
        } else {
            return 0;
        }
    }

    public Customer getCustomerFromSupermarket() {
        if (this.supermarket.getCustomers().size() > 1) {
            Random random = new Random();
            int id = random.nextInt(this.supermarket.getCustomers().size() - 1);
            return this.supermarket.getCustomers().get(id);
        } else {
            return this.supermarket.getCustomers().get(0);
        }
    }

    private void fillProductAccess() {

        productAccess.put("Cigarette", true);
        productAccess.put("Beer", true);
        productAccess.put("Doshik", false);
        productAccess.put("Cheese", false);
        productAccess.put("Sausage", false);
        productAccess.put("Milk", false);
        productAccess.put("Chocolate", false);
        productAccess.put("Apple", false);
        productAccess.put("Clips", false);
    }

    private void fillProductCountable() {
        productCountable.put("Cigarette", true);
        productCountable.put("Beer", true);
        productCountable.put("Doshik", true);
        productCountable.put("Cheese", false);
        productCountable.put("Sausage", false);
        productCountable.put("Milk", false);
        productCountable.put("Chocolate", true);
        productCountable.put("Apple", false);
        productCountable.put("Clips", true);
    }

    private void fillNamesList() {
        this.names.add("Igritt");
        this.names.add("Eddard");
        this.names.add("John");
        this.names.add("Drogo");
        this.names.add("Jaime");
        this.names.add("Daineries");
        this.names.add("Sansa");
    }

    private void fillLastNamesList() {
        this.lastNames.add("Stark");
        this.lastNames.add("Snow");
        this.lastNames.add("Lanister");
        this.lastNames.add("Targarien");
    }


    private void fillStatusList() {
        this.statusList.add("Child");
        this.statusList.add("Adult");
        this.statusList.add("Retired");
    }

    private double createPrice() {
        return Math.random() * 500;
    }

    private Integer createAmount() {
        Random random = new Random();
        return 1 + random.nextInt(20);
    }

    private String createName() {
        Random random = new Random();
        int id = random.nextInt(this.names.size() - 1);
        return this.names.get(id);
    }


    private String createLastName() {
        Random random = new Random();
        int id = random.nextInt(this.lastNames.size() - 1);
        return this.lastNames.get(id);
    }


    private String createStatus() {
        Random random = new Random();
        int id = random.nextInt(this.statusList.size());
        return this.statusList.get(id);
    }


}
