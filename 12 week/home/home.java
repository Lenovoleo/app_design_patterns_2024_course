import java.util.Scanner;

interface TicketMachineState {
    void selectTicket();
    void insertMoney(int amount);
    void dispenseTicket();
    void cancelTransaction();
}

class IdleState implements TicketMachineState {
    private TicketMachine machine;

    public IdleState(TicketMachine machine) {
        this.machine = machine;
    }

    @Override
    public void selectTicket() {
        System.out.println("Вы выбрали билет. Пожалуйста, внесите деньги.");
        machine.setState(machine.getWaitingForMoneyState());
    }

    @Override
    public void insertMoney(int amount) {
        System.out.println("Сначала выберите билет.");
    }

    @Override
    public void dispenseTicket() {
        System.out.println("Билет не выбран.");
    }

    @Override
    public void cancelTransaction() {
        System.out.println("Вы ещё ничего не сделали.");
    }
}

class WaitingForMoneyState implements TicketMachineState {
    private TicketMachine machine;

    public WaitingForMoneyState(TicketMachine machine) {
        this.machine = machine;
    }

    @Override
    public void selectTicket() {
        System.out.println("Билет уже выбран. Внесите деньги.");
    }

    @Override
    public void insertMoney(int amount) {
        machine.addMoney(amount);
        if (machine.isEnoughMoney()) {
            System.out.println("Достаточно денег. Билет выдаётся.");
            machine.setState(machine.getMoneyReceivedState());
        } else {
            System.out.println("Недостаточно денег. Внесите ещё " + (machine.getTicketPrice() - machine.getCurrentMoney()) + " тенге.");
        }
    }

    @Override
    public void dispenseTicket() {
        System.out.println("Внесите деньги, чтобы получить билет.");
    }

    @Override
    public void cancelTransaction() {
        System.out.println("Транзакция отменена. Возврат внесённых денег: " + machine.getCurrentMoney() + " тенге.");
        machine.resetMoney();
        machine.setState(machine.getIdleState());
    }
}

class MoneyReceivedState implements TicketMachineState {
    private TicketMachine machine;

    public MoneyReceivedState(TicketMachine machine) {
        this.machine = machine;
    }

    @Override
    public void selectTicket() {
        System.out.println("Вы уже внесли деньги. Получите билет.");
    }

    @Override
    public void insertMoney(int amount) {
        System.out.println("Деньги уже внесены. Получите билет.");
    }

    @Override
    public void dispenseTicket() {
        System.out.println("Выдаётся билет: " + machine.getSelectedTicketName());
        machine.resetMoney();
        machine.setState(machine.getIdleState());
    }

    @Override
    public void cancelTransaction() {
        System.out.println("Транзакцию нельзя отменить после внесения денег.");
    }
}

class TransactionCanceledState implements TicketMachineState {
    private TicketMachine machine;

    public TransactionCanceledState(TicketMachine machine) {
        this.machine = machine;
    }

    @Override
    public void selectTicket() {
        System.out.println("Транзакция отменена. Начните сначала.");
        machine.setState(machine.getIdleState());
    }

    @Override
    public void insertMoney(int amount) {
        System.out.println("Транзакция отменена. Начните сначала.");
    }

    @Override
    public void dispenseTicket() {
        System.out.println("Транзакция отменена. Начните сначала.");
    }

    @Override
    public void cancelTransaction() {
        System.out.println("Транзакция уже отменена.");
    }
}

class TicketMachine {
    private TicketMachineState idleState;
    private TicketMachineState waitingForMoneyState;
    private TicketMachineState moneyReceivedState;
    private TicketMachineState currentState;

    private int ticketPrice = 100; 
    private int currentMoney = 0; 
    private String selectedTicketName = "Наурыз Мейрамы"; 

    public TicketMachine() {
        idleState = new IdleState(this);
        waitingForMoneyState = new WaitingForMoneyState(this);
        moneyReceivedState = new MoneyReceivedState(this);

        currentState = idleState; 
    }

    public void setState(TicketMachineState state) {
        this.currentState = state;
    }

    public TicketMachineState getIdleState() {
        return idleState;
    }

    public TicketMachineState getWaitingForMoneyState() {
        return waitingForMoneyState;
    }

    public TicketMachineState getMoneyReceivedState() {
        return moneyReceivedState;
    }

    public void selectTicket() {
        currentState.selectTicket();
    }

    public void insertMoney(int amount) {
        currentState.insertMoney(amount);
    }

    public void dispenseTicket() {
        currentState.dispenseTicket();
    }

    public void cancelTransaction() {
        currentState.cancelTransaction();
    }

    public void addMoney(int amount) {
        currentMoney += amount;
    }

    public boolean isEnoughMoney() {
        return currentMoney >= ticketPrice;
    }

    public int getCurrentMoney() {
        return currentMoney;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void resetMoney() {
        currentMoney = 0;
    }

    public String getSelectedTicketName() {
        return selectedTicketName;
    }
}

public class home {
    public static void main(String[] args) {
        TicketMachine machine = new TicketMachine();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Выбрать билет");
            System.out.println("2. Внести деньги");
            System.out.println("3. Получить билет");
            System.out.println("4. Отменить транзакцию");
            System.out.println("5. Выйти");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> machine.selectTicket();
                case 2 -> {
                    System.out.print("Введите сумму: ");
                    int amount = scanner.nextInt();
                    machine.insertMoney(amount);
                }
                case 3 -> machine.dispenseTicket();
                case 4 -> machine.cancelTransaction();
                case 5 -> {
                    System.out.println("До свидания!");
                    return;
                }
                default -> System.out.println("Неверный выбор.");
            }
        }
    }
}
