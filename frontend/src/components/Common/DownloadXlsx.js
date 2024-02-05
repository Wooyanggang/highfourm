import * as xlsx from 'xlsx';

const downloadXlsx = (data, columnValueArr, sheetName, fileName) => {
  // data : json 데이터, columnValueArr : 컬럼명 순서대로
  const ws = xlsx.utils.json_to_sheet(data);
  const wb = xlsx.utils.book_new();

  columnValueArr.forEach((x, idx) => {
    const cellAdd = xlsx.utils.encode_cell({ c: idx + 1, r: 0 });
    ws[cellAdd].v = x;
  });
  ws['!cols'] = [];
  ws['!cols'][0] = { hidden: true };
  xlsx.utils.book_append_sheet(wb, ws, sheetName);
  xlsx.writeFile(wb, fileName);
}

export default downloadXlsx;