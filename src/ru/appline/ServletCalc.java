package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import ru.appline.logic.Abstract;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/math")
public class ServletCalc extends HttpServlet {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");

        request.setCharacterEncoding("utf-8");



        StringBuffer sb = new StringBuffer();

        PrintWriter pw = response.getWriter();
        Abstract Abstract = new Abstract();

        String line;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            System.out.println("Ошибка");
        }

        JsonObject jObj = gson.fromJson(String.valueOf(sb), JsonObject.class);

        double number1 = jObj.get("a").getAsDouble();

        double number2 = jObj.get("b").getAsDouble();

        String resul = jObj.get("math").getAsString();

        switch (resul) {
            case "+":
                Abstract.result = number1 + number2;
                pw.print(gson.toJson(Abstract));
                break;
            case "-":
                Abstract.result = number1 - number2;
                pw.print(gson.toJson(Abstract));
                break;
            case "*":
                Abstract.result = number1 * number2;
                pw.print(gson.toJson(Abstract));
                break;
            case "/":
                Abstract.result = number1 / number2;
                pw.print(gson.toJson(Abstract));
                break;

        }
    }
}
