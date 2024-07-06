import React from "react";
import { dateString } from "../../helpers/helpers";
function ClubCard({ data }) {
  if (!data) return <></>;
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
        <p>Date of creation:</p> {dateString(data.dateOfCreation)}
      </p>
    </>
  );
}

export default ClubCard;
