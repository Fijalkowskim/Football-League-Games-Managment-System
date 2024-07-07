import React from "react";
import { dateString, formatDate } from "../../helpers/helpers";
function ClubCard({ data }) {
  if (!data) return <></>;
  console.log(data.dateOfCreation);
  return (
    <>
      <p>
        <p>ID:</p> {data.id}
      </p>
      <p>
        <p>Name:</p> {data.name}
      </p>
      <p>
        <p>Location:</p> {data.location}
      </p>
      <p>
        <p>Date of creation:</p>{" "}
        {typeof data.dateOfCreation === "string"
          ? data.dateOfCreation
          : data.dateOfCreation.toDateString()}
      </p>
    </>
  );
}

export default ClubCard;
