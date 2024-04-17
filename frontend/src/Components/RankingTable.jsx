//Author: Mateusz Dyla, https://mui.com/x/react-data-grid/

import * as React from 'react';
import { DataGrid } from '@mui/x-data-grid';
import "../styles/RankingTableStyle.css"


const columns = [
  { field: 'id', headerName: 'Miejsce', width: 100 },
  { field: 'name', headerName: 'Nazwa', width: 150 },
  { field: 'time', headerName: 'Czas', width: 100 },
  {field: 'score', headerName: 'Odwiedzone bary', width: 200},
];

const rows = [
  { id: 1, name: 'Marek34',   score: 35, time: '23:40' },
  { id: 2, name: 'Tomek5',    score: 42, time: '120:32' },
  { id: 3, name: 'Marek34',   score: 45, time: '120:32' },
  { id: 4, name: 'Bożena12',  score: 16, time: '120:32' },
  { id: 5, name: 'xd123',     score: 2, time: '15:00' },
  { id: 6, name: 'student12', score: 12, time: '140:23' },
  { id: 7, name: 'Clifford',  score: 10, time: '140:23' },
  { id: 8, name: 'franekxd',  score: 16, time: '140:23' },
  { id: 9, name: 'Roxie',     score: 10, time: '140:23' },
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