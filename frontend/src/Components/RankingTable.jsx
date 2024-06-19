//Author: Mateusz Dyla, https://mui.com/x/react-data-grid/

import * as React from "react";
import { DataGrid } from "@mui/x-data-grid";
import "../styles/RankingTableStyle.css";
import { useEffect } from "react";
import { useState } from "react";

const columns = [
  { field: "id", headerName: "id", width: 10 },
  { field: "username", headerName: "Nazwa", width: 150 },
  { field: "gameplayTime", headerName: "Czas", width: 100 },
  { field: "visitedBarsNumber", headerName: "Odwiedzone bary", width: 200 },
];

export default function DataTable() {
  const [records, setRecords] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/ranking/")
      .then((response) => response.json())
      .then((data) => setRecords(data))
      .catch((err) => console.log(err));
  }, []);

  return (
    <div id="ranking-table">
      <DataGrid
        rows={records}
        columns={columns}
        getRowId={(row) => row.id}
        initialState={{
          pagination: {
            paginationModel: { page: 0, pageSize: 5 },
          },
        }}
        pageSizeOptions={[5, 10]}
      />
    </div>
  );
}
