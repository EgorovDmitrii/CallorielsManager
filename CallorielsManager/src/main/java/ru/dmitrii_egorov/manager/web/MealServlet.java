package ru.dmitrii_egorov.manager.web;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;
import ru.dmitrii_egorov.manager.model.Meal;
import ru.dmitrii_egorov.manager.model.MealTo;
import ru.dmitrii_egorov.manager.repository.MealRepository;
import ru.dmitrii_egorov.manager.repository.inmemory.InMemoryMealRepository;
import ru.dmitrii_egorov.manager.service.MealService;
import ru.dmitrii_egorov.manager.util.MealsUtil;
import ru.dmitrii_egorov.manager.util.SecurityUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

@WebServlet("/meals")
public class MealServlet extends HttpServlet {

    private ConfigurableApplicationContext springContext;
    private MealService mealService;

    @Override
    public void init() throws ServletException {
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
        this.mealService = springContext.getBean(MealService.class);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String idStr = req.getParameter("id");

        Meal meal = new Meal(
                idStr.isEmpty() ? null : Integer.parseInt(idStr),
                LocalDateTime.parse(req.getParameter("dateTime")),
                req.getParameter("description"),
                Integer.parseInt(req.getParameter("calories"))
        );

        if (meal.isNew()) {
            mealService.create(meal, SecurityUtil.authUserID());
        } else {
            mealService.update(meal, SecurityUtil.authUserID());
        }
        resp.sendRedirect("meals");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        switch (action == null ? "all" : action) {
            case "create":
            case "update":
                handleCreateOrUpdate(req, resp, action);
                break;
            case "delete":
                handleDelete(req, resp);
                break;
            case "all":
            default:
                handleGetAll(req, resp);
                break;
        }
    }

    private void handleGetAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Meal> meals = mealService.getAll(SecurityUtil.authUserID());
        List<MealTo> mealsTO = MealsUtil.getTos(meals, 2000);

        req.setAttribute("meals", mealsTO);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/meals.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void handleDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        mealService.delete(getId(req), SecurityUtil.authUserID());
        resp.sendRedirect("meals");
    }

    private void handleCreateOrUpdate(HttpServletRequest req, HttpServletResponse resp, String action) throws ServletException, IOException {
        Meal meal = action.equals("create")
                ? new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000)
                : mealService.get(getId(req), SecurityUtil.authUserID());

        req.setAttribute("meal", meal);
        req.getRequestDispatcher("/jsp/mealForm.jsp").forward(req, resp);
    }

    private int getId(HttpServletRequest req) {
        String idStr = Objects.requireNonNull(req.getParameter("id"));
        return Integer.parseInt(idStr);
    }
}
