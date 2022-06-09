import {Button, IconButton, TextField} from "@mui/material";
import PersonAddAltIcon from '@mui/icons-material/PersonAddAlt';
import {Employee} from "../../EmployeeForm/EmployeeForm.types";
import './TableToolbar.css';

export type TableToolbarProps = {
    pageNumber: number;
    totalPages: number;
    setPageNumber(newNumber: number): void;
    searchQuery :string;
    setSearchQuery(newQuery: string):void;
}

export function TableToolbar(props: TableToolbarProps){
    return (<div className={"employee-list__table-toolbar"}>
        <TextField
            value={props.searchQuery}
            label={'Search'}
            onChange={(e) => {
               props.setSearchQuery(e.currentTarget.value)
            }}
        />
        <Button
            variant={"contained"}
            onClick={() => {
                if(props.pageNumber != 0){
                    props.setPageNumber(props.pageNumber - 1);
                }
            }}
        >{"<"}</Button>
        <span>{props.pageNumber}</span>
        <Button
            variant={"contained"}
            onClick={() => {
                if(props.pageNumber != props.totalPages - 1){
                    props.setPageNumber(props.pageNumber + 1);
                }
            }}
        >{">"}</Button>
        <IconButton href={"/employee"}><PersonAddAltIcon/></IconButton>
    </div>)
}