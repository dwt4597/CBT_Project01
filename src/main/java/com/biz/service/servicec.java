package com.biz.service;

import java.io.*;
import java.util.*;

import org.apache.ibatis.session.*;

import com.biz.dao.*;
import com.biz.db.*;
import com.biz.vo.*;

public class servicec {
	SqlSessionFactory sessionFactory;

	Scanner scan;
	List<CbtVO> insertList = new ArrayList();
	List<CbtVO> updateList = new ArrayList();
	List<CbtVO> findById = new ArrayList();

	public servicec() {
		OracleSqlFactory sqlFactory = new OracleSqlFactory();
		this.sessionFactory = sqlFactory.getSessionFactory();
		scan = new Scanner(System.in);
	}

	public void QuesExplain() {
		SqlSession session = this.sessionFactory.openSession();

		// Dao 받기
		CbtDao dao = session.getMapper(CbtDao.class);
		int index = 0;

		while (true) {

			List<CbtVO> ret = dao.selectAll();
			System.out.println("====================");
			System.out.println(ret.get(index).getCb_ques());
			System.out.println("-------------------");
			String[] ex = ret.get(index).getCb_ex().split("/");
			for (String s : ex) {
				System.out.println(s);

			}
			System.out.println("-----------------");
			System.out.println("정답 >> ");
			String s = scan.nextLine();
			int a = Integer.valueOf(s);
			if (ex[a - 1].equals(ret.get(index).getCb_ans())) {
				System.out.println("※정답입니다(Enter:다음문제풀기)");
				index++;
			} else {
				System.out.println("※틀렸습니다(1:다시풀기, Enter:다음문제풀기");
				index++;

				continue;
			}

		}

	}

	public void startMenu() {

		System.out.println("1. 시작메뉴");
		System.out.println("==============================");
		System.out.println("1.문제입력\t 2.문제풀이\t 0.종료");
		System.out.println("------------------------------");
		System.out.print("선택 >>");
		String select_SM = scan.nextLine();
		if (select_SM.equals("1")) {
			System.out.println("1.문제입력으로 갑니다...");
			quesionMenu();
		} else if (select_SM.equals("2")) {
			QuesExplain();
		} else if (select_SM.equals("0")) {
			return;
		}

	}

	public void quesionMenu() {
		System.out.println("2.문제입력");
		System.out.println("==============================");
		System.out.println("1.문제등록\t 2.문제수정\t 3.문제삭제\t 4.종료");
		System.out.println("------------------------------");
		System.out.print("선택 >>");
		String select_SM = scan.nextLine();
		if (select_SM.equals("1")) {
			insertQue();
		} else if (select_SM.equals("2")) {
			updateQue();
		} else if (select_SM.equals("3")) {
			deleteQue();
		} else if (select_SM.equals("0")) {
			return;
		}
	}

	public void delete(String id) {
		// DB로부터 값을 가져오기
		SqlSession session = this.sessionFactory.openSession();

		// Dao 받기
		CbtDao dao = session.getMapper(CbtDao.class);

		int ret = dao.delete(id);
		session.commit();
		session.close();
		System.out.println("삭제되었습니다");
	}

	public void deleteQue() {
		System.out.println("삭제할 Id 입력 >> ");
		String s = scan.nextLine();

		delete(s);
	}

	public void update(CbtVO vu) {
		SqlSession session = this.sessionFactory.openSession();

		// Dao 받기
		CbtDao dao = session.getMapper(CbtDao.class);

		int ret = dao.update(vu);
		session.commit();
		session.close();
		System.out.println("1111");
	}

	public void updateQue() {
		System.out.println("수정할 Id입력?>>");
		String s = scan.nextLine();
		CbtVO vu = new CbtVO();
		vu.setCb_id(s);

		System.out.println("수정할 Id : " + vu.getCb_id());

		System.out.println("수정내용 입력>>");
		String z = scan.nextLine();
		vu.setCb_ques(z);
		System.out.println(vu.getCb_ques());
		this.update(vu);
	}

	public int insert(CbtVO vo) {
		// DB로부터 값을 가져오기
		SqlSession session = this.sessionFactory.openSession();

		// Dao 받기
		CbtDao dao = session.getMapper(CbtDao.class);

		int ret = dao.insert(vo);
		session.commit();
		session.close();

		return ret;
	}

	public void insertQue() {
		String txt = "src/main/java/com/biz/CBT/ques.txt";

		FileReader fr;
		BufferedReader buffer;

		try {
			fr = new FileReader(txt);
			buffer = new BufferedReader(fr);

			while (true) {
				String line = buffer.readLine();
				if (line == null)
					break;
				String[] lines = line.split(":");

				CbtVO vo = new CbtVO();
				vo.setCb_ques(lines[0]);
				vo.setCb_ex(lines[1]);
				vo.setCb_ans(lines[2]);
				insertList.add(vo);

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (CbtVO v : insertList) {
			insert(v);
		}
	}

}
