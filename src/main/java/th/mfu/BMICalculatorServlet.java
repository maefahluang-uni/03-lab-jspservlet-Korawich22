package th.mfu;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//TODO: add webservlet to "/calbmi"
@WebServlet(urlPatterns = "/calbmi")
public class BMICalculatorServlet extends HttpServlet{

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO: get parameter from request: "weight" and "height"
        String weightStr = request.getParameter("weight");
        String heightStr = request.getParameter("height");
        if (weightStr != null && !weightStr.isEmpty() && heightStr != null && !heightStr.isEmpty()) {
            try{
        double weight = Double.parseDouble(weightStr);
        double height = Double.parseDouble(heightStr);
        
        //TODO: calculate bmi
        
        double bmi = Math.round(calculateBMI(weight,height));


        //TODO: determine the built from BMI
        String built = determineBuilt(bmi);
      
        //TODO: add bmi and built to the request's attribute
        request.setAttribute("bmi", bmi);
        request.setAttribute("built", built);

        //TODO: forward to jsp
        request.getRequestDispatcher("/bmi_result.jsp").forward(request, response);
            }catch (NumberFormatException e)
            {
                response.getWriter().println("Invalid weight or height value");
            }
        }
    }
    

    private String determineBuilt(Double bmi) {
        if(bmi<18.5){
             return "underweight";
        } else if(bmi>=18.5 && bmi<25){
            return "normal";
        } else if(bmi>=25 && bmi<30){
            return "overweight";
        } else if(bmi>=30 && bmi<35){
            return "obese";
        } else {
            return "extremely obese";
        }
    }

    private Double calculateBMI(Double weight, Double height) {

        return weight / (height * height);
    }
    
}
