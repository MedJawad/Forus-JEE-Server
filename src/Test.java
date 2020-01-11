import beans.Group;
import beans.Post;
import beans.User;
import business.CommentBusiness;
import business.GroupBusiness;
import business.PostBusiness;
import business.UserBusiness;
import business.imp.CommentBusinessImp;
import business.imp.GroupBusinessImp;
import business.imp.PostBusinessImp;
import business.imp.UserBusinessImp;
import dao.CommentDao;
import dao.GroupDao;
import dao.PostDao;
import dao.UserDao;
import dao.imp.CommentDaoImp;
import dao.imp.GroupDaoImp;
import dao.imp.PostDaoImp;
import dao.imp.UserDaoImp;

public class Test {

	public static void main(String[] args) {
		UserDao udao = new UserDaoImp();
		UserBusiness uBus = new UserBusinessImp(udao);

		PostDao pdao = new PostDaoImp();
		PostBusiness pBus = new PostBusinessImp(pdao);
		
		GroupDao gdao = new GroupDaoImp();
		GroupBusiness gBus = new GroupBusinessImp(gdao);
		
		CommentDao cdao = new CommentDaoImp();
		CommentBusiness cBus = new CommentBusinessImp(cdao);
		
		User u1 = uBus.create("jawad", "jawad@jawad.jawad", "jawad");
		User u2 = uBus.create("jawadi", "jawadi@jawadi.jawadi", "jawadi");
		
		Group g1 = gBus.create("Science", "A group for Science nerds", u1);
		
		Post p1 = pBus.create("What was a house rule you had as a kid that you thought was completely normal until you grew up and realized not all households followed?\r\n", "Wakey wakey time. No noise annoying mum and dad until 7am. This was a really necessary one bc I was a very early riser as a kid and I loved to talk to my parents. The rule was that I could come to their bed and curl up next to them, but I couldn't talk until 7. It was actually really nice, and made sure I got enough rest myself."
								,u1);
		Post p2 = pBus.create("I wasn't allowed to take a shower if I was home alone.", "I also learned at age 7 that other households closed the bathroom door when using the toilet and keeping it open was weird. I learned that by keeping the door open at a friend's house, a friend walked by the bathroom, saw me, told their mom, their mom called my mom, and all of a sudden the new house rule was we close the bathroom door when using the toilet.\r\n"
					,u1,g1);
		Post p3 = pBus.create("My mom freaks out about hearing commercials on TV. ", "The second it goes to commercial break it has to be muted. She also mutes it if she thinks it's about to go to commercial, even though sometimes she's wrong."
								,u2,g1);

		cBus.create("this is an amazing post", p2, u2);
		cBus.create("this is an amazing post", p3, u1);
	}

}
