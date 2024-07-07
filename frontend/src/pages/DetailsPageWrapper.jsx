import React from "react";
import LoadingBar from "../components/general/LoadingBar";

function DetailsPageWrapper({ children, header, isPending, data }) {
  return (
    <div className="mt-24 flex flex-col justify-center items-center gap-4 p-2">
      <h1 className="text-2xl">{header}</h1>
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
