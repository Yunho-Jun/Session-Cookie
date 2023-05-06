package org.zerock.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.zerock.w2.dao.TodoDAO;
import org.zerock.w2.domain.TodoVO;

import java.time.LocalDate;
import java.util.List;

public class TodoDAOTests {

    private TodoDAO todoDao;

    @BeforeEach
    public void ready(){
        todoDao = new TodoDAO();
    }
    @Test
    public void testTime() throws Exception{

        System.out.println(todoDao.getTime());
    }

    @Test
    public void testInsert()throws Exception{
        TodoVO todoVO = TodoVO.builder()
                .title("SAMPLE TITLE..")
                .dueDate(LocalDate.of(2023,04,27))
                .build();

        todoDao.insert(todoVO);
    }

    @Test
    public void testList() throws Exception {

    List<TodoVO> list = todoDao.selectAll();
    list.forEach(vo-> System.out.println(vo));
    }

    @Test
    public void testSelectOne()throws Exception{

        Long tno = 1L;

        TodoVO vo  = todoDao.selectOne(tno);

        System.out.println(vo);

    }
    @Test
    public void testUpdateOne() throws Exception{
        TodoVO todoVO = TodoVO.builder()
                .tno(1L).title("Sample Title...")
                .dueDate(LocalDate.of(2021,12,31))
                .finished(true)
                .build();

        todoDao.updateOne(todoVO);

    }
}
