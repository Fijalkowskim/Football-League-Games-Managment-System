package pl.take.football_league.rest;


import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import pl.take.football_league.ejb.ClubEJB;
import pl.take.football_league.entities.Club;

@Path("/club")
@Consumes({ "application/json" })
@Produces({ "application/json" })

//@Consumes({ "application/xml" })
//@Produces({ "application/xml" })

public class ClubREST {

	@EJB
	ClubEJB bean;

	//@Override
	@POST
	public String create(Club club) {
		bean.create(club);
		return "club created!";
	}

	//@Override
	@GET
	@Path("/{idc}")
	public Club find(@PathParam("idc") int idc) {
		Club club = bean.find(idc);
		return club;
	}

	//@Override
	@GET
	public List<Club> get() {
		List<Club> clubList = bean.get();
		return clubList;
	}

	//@Override
	@PUT
	public String update(Club club) {
		try {
			bean.update(club);
			return "club updated!";
		} catch (Exception e) {
			e.printStackTrace();
			return "club not updated :(";
		}
	}


	//@Override
	@DELETE
	@Path("/{idc}")
	public void delete(@PathParam("idc") int idc) {
		bean.delete(idc);
	}


}
