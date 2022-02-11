package Converter.model;

import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class Bot extends TelegramLongPollingBot {
    ServiceBot serviceBot = new ServiceBot();

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {

        System.out.println(update);
        System.out.println(update.getMessage().getText());

        if (update.getMessage().getText().equals("/start")) {
            ServiceBot.Headerr(update);
            execute(ServiceBot.Headerr(update));
        } else if (ServiceBot.ketmoncha == 0) {
            execute(ServiceBot.Tekshirish(update));
        } else {
            execute(ServiceBot.Usduzs(update));
        }



    }


    @Override
    public String getBotUsername() {
        return "t.me/sen_kettin_najimBot";
    }

    @Override
    public String getBotToken() {
        return "1611081845:AAFmW2mWE4FnVJkmorzDfbpslrJxsbgwrN4";
    }

}
