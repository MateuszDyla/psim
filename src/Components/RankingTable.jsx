import * as React from 'react';
import { DataGrid } from '@mui/x-data-grid';
import "../styles/RankingTablestyle.css"


const columns = [
  { field: 'id', headerName: 'Miejsce', width: 150 },
  { field: 'name', headerName: 'Nazwa', width: 150 },
  { field: 'score', headerName: 'Wynik', width: 150 },
];

const rows = [
  { id: 1, name: 'Snow', score: 35 },
  { id: 2, name: 'Lannister', score: 42 },
  { id: 3, name: 'Lannister', score: 45 },
  { id: 4, name: 'Stark', score: 16 },
  { id: 5, name: 'Targaryen', score: null },
  { id: 6, name: 'Melisandre', score: 150 },
  { id: 7, name: 'Clifford', score: 44 },
  { id: 8, name: 'Frances', score: 36 },
  { id: 9, name: 'Roxie', score: 65 },
];

export default function DataTable() {
  return (
    <div id="ranking-table">
      <DataGrid
        rows={rows}
        columns={columns}
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