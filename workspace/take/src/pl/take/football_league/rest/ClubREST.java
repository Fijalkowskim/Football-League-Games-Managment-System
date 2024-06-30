package pl.take.football_league.rest;


import java.util.ArrayList;
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

import pl.take.football_league.dtos.*;
import pl.take.football_league.ejb.*;
import pl.take.football_league.entities.*;

@Path("/clubs")
@Consumes({ "application/json" })
@Produces({ "application/json" })

//@Consumes({ "application/xml" })
//@Produces({ "application/xml" })

public class ClubREST {

	@EJB
	ClubEJB bean;

	@POST
	public String create(CreateClubDto clubDto) {
		bean.create(clubDto);
		return "Club created!";
	}

	@GET
	@Path("/{idc}")
	public ReturnClubDto find(@PathParam("idc") int idc) {
		ReturnClubDto clubDto = bean.find(idc);
		return clubDto;
	}

	@GET
	public List<ReturnClubDto> get() {
		List<ReturnClubDto> clubListDto = bean.get();
		return clubListDto;
	}

	@PUT
	@Path("/{idc}")
	public String update(@PathParam("idc") int idc, UpdateClubDto clubDto) {
		try {
			bean.update(idc, clubDto);
			return "Club updated!";
		} catch (Exception e) {
			e.printStackTrace();
			return "Club not updated.";
		}
	}

	@DELETE
	@Path("/{idc}")
	public void delete(@PathParam("idc") int idc) {
		bean.delete(idc);
	}

}
