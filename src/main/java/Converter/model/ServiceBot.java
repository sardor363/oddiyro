package Converter.model;

import com.google.gson.Gson;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServiceBot {
    static int ketmoncha = 0;
    static int counter = 0;

    public static SendMessage Headerr(Update update) {
        String text = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();
        SendMessage sendMessage = new SendMessage();
        if (update.hasMessage()) {
            if (text.equals("/start")) {
            }
            sendMessage.setText("Sen kettin NAJM !(Bot ishga tushdi =) )");
            sendMessage.setChatId(chatId);


            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
            replyKeyboardMarkup.setSelective(true);
            replyKeyboardMarkup.setResizeKeyboard(true);
            replyKeyboardMarkup.setOneTimeKeyboard(false);
// Klaviatura qatorlari listi
            List<KeyboardRow> keyboard = new ArrayList<>();
// Klaviatura birinchi qatori
            KeyboardRow row1 = new KeyboardRow();
            KeyboardRow row2 = new KeyboardRow();


            row1.add("UZS-USD");
            row1.add("USD-UZS");
            row2.add("UZS-EU");
            row2.add("EU-UZS");

            keyboard.add(row1);
            keyboard.add(row2);

            replyKeyboardMarkup.setKeyboard(keyboard);
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
            return sendMessage;
        }
        return sendMessage;

    }

    public static SendMessage Tekshirish(Update update) throws IOException {
        ketmoncha = 10;
        SendMessage message = new SendMessage();
        Gson gson = new Gson();
        if (update.getMessage().getText().equals("UZS-USD")) {
            message.setText("Qiymatni kiriting: ");
            message.setChatId(update.getMessage().getChatId());
            counter = 1;
        }  if (update.getMessage().getText().equals("USD-UZS")) {
            message.setText("Qiymatni kiriting: ");
            message.setChatId(update.getMessage().getChatId());
            counter = 2;
        }  if (update.getMessage().getText().equals("UZS-EU")) {
            message.setText("Qiymatni kiriting : ");
            message.setChatId(update.getMessage().getChatId());
            counter = 3;
        }  if (update.getMessage().getText().equals("EU-UZS")) {

            message.setText("Qiymatni kiriting: ");
            message.setChatId(update.getMessage().getChatId());
            counter = 4;
        }


        return message;
    }

    public static SendMessage Usduzs(Update update) throws IOException {
        SendMessage sendMessage = new SendMessage();

        SendMessage message = new SendMessage();
        Long chatId = update.getMessage().getChatId();
        message.setChatId(chatId);
        Gson gson = new Gson();


        URL url = new URL("https://cbu.uz/oz/arkhiv-kursov-valyut/json/");
        URLConnection urlConnection = url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        ResponseItem[] responseItem = gson.fromJson(reader, ResponseItem[].class);
        List<ResponseItem> responseItems = Arrays.asList(responseItem);
        double aDouble = Double.parseDouble(update.getMessage().getText());
        if (counter == 1) {
            String dolllar = update.getMessage().getText();
            double v = Double.parseDouble(responseItems.get(0).getRate());
            message.setText(String.valueOf((Double.parseDouble(dolllar)) / v));

        } else if (counter == 2) {
            String dolllar = update.getMessage().getText();
            double v = Double.parseDouble(responseItems.get(0).getRate());
            message.setText(String.valueOf((Double.parseDouble(dolllar)) * v));
        }
        else if (counter == 3) {
            String euro = update.getMessage().getText();
            double v = Double.parseDouble(responseItems.get(1).getRate());
            message.setText(String.valueOf((Double.parseDouble(euro)) / v));
        }
        else if (counter == 4) {
            String euro = update.getMessage().getText();
            double v = Double.parseDouble(responseItems.get(1).getRate());
            message.setText(String.valueOf((Double.parseDouble(euro)) * v));
        }

        ReplyKeyboardRemove replyKeyboardRemove = new ReplyKeyboardRemove();
        sendMessage.setReplyMarkup(replyKeyboardRemove);
        return message;
    }
}
