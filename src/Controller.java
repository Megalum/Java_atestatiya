import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Logger;
import java.io.*;

public class Controller {
    Machine machine = new Machine();

    public void machineON(){
        machine.addToys(new Toys("Медведь", 500));
        machine.addToys(new Toys("Заяц", 300));
        machine.addToys(new Toys("Волк", 100));
        machine.addToys(new Toys("Енот", 1500));
    }

    private void log(String str) throws IOException {
        try(FileWriter writer = new FileWriter("Log.txt", true)){
            writer.write(str);
            writer.append('\n');
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }


    }

    public void buttonClick() throws IOException {

        Logger logger = Logger.getAnonymousLogger();
        Scanner in = new Scanner(System.in);
        Random random = new Random();
        String task;
        while (true){
            logger.info("Введите команду:" +
                    "\n1 - Проверить удачу" +
                    "\n2 - Добавить игрушку" +
                    "\n3 - Показ списка игрушек" +
                    "\n4 - Выход");
            task = in.next();
            if (task.equals("1")){
                int attempt = random.nextInt(100) - 50;

                if (attempt > 0){
                    attempt = random.nextInt(100000);
                    ArrayList<Integer> odds = new ArrayList<>();
                    odds.add(0);
                    int i = 0;
                    for (Toys toys: machine.assort) {
                        i += toys.getCount();
                        odds.add(i);
                    }
                    double chance = 100000f / i;
                    double start, end;
                    for (i = 1; i < odds.size(); i++){
                        start = chance * odds.get(i - 1);
                        end = chance * odds.get(i);
                        if (start < attempt && end > attempt){
                            Date date = new Date();
                            String message = "выйграли: " + machine.assort.get(i - 1).getName();
                            logger.info("Вы " + message);
                            machine.assort.get(i - 1).Win();
                            new Controller().log(message + ": " + date.toString());
                            break;
                        }
                    }
                }else {
                    Date date = new Date();
                    logger.info("Вы проиграли");
                    new Controller().log("проиграли: " + date.toString());
                }

            } else if (task.equals("2")) {

                logger.info("Введите название игрушки: ");
                String name = in.next();
                logger.info("Введите колличество игрушкек: ");
                int count = in.nextInt();
                int key = 0;
                for (Toys toys: machine.assort) {
                    if (name.equals(toys.getName()))
                    {
                        toys.add(count);
                        key = 1;
                        break;
                    }
                }
                if (key == 0){
                    machine.addToys(new Toys(name, count));
                }

            } else if (task.equals("3")) {
                for (Toys toys: machine.assort) {
                    logger.info(toys.toString());
                }
            } else if (task.equals("4")) {
                break;
            }
        }
    }
}
