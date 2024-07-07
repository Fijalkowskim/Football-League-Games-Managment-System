import React from "react";
import LoadingBar from "../components/general/LoadingBar";
import { NavLink } from "react-router-dom";
import CustomButton from "../components/general/CustomButton";
import { MdOutlineModeEdit } from "react-icons/md";
function DetailsPageWrapper({
  children,
  header,
  isPending,
  data,
  editPageLink,
}) {
  return (
    <div className="mt-24 flex flex-col justify-center items-center gap-4 p-2">
      <div className="flex gap-3 items-center justify-center">
        <h1 className="text-2xl">{header}</h1>
        {data && (
          <NavLink to={`${editPageLink}/${data.id}`}>
            <CustomButton>
              <MdOutlineModeEdit />
            </CustomButton>
          </NavLink>
        )}
      </div>
      {isPending ? (
        <LoadingBar />
      ) : data !== undefined ? (
        children
      ) : (
        <p>No data yet</p>
      )}
    </div>
  );
}

export default DetailsPageWrapper;
