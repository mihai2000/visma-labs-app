import {EmployeeListProps} from "./EmployeeList";
import {useEffect, useState} from "react";
import {Employee} from "../EmployeeForm/EmployeeForm.types";
import {DEFAULT_PAGE, Page} from "./Page";

export function useEmployeeList(): EmployeeListProps{
    const [searchQuery, setSearchQuery] = useState("");
    const [pageNumber, setPageNumber] = useState<number>(0);
    const [pageSize, setPageSize] = useState<number>(5);

    const [page, setPage] = useState<Page<Employee>>(DEFAULT_PAGE);

    async function fetchData(){

        const params = new URLSearchParams();
        params.set("name",searchQuery);
        params.set("pageNumber", "" + pageNumber);
        params.set("pageSize", "" + pageSize);

        /*const newEmployees = await window.fetch("/api/employee/", {
            method: "GET",
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(r => r.json())
            .then(json => json.content);*/

        const response = await window.fetch("/api/employee/search?" + params.toString(), {
            method: "GET",
            headers: {
                'Content-Type': 'application/json'
            }
        })
        const newPage = await response.json();

        setPage(newPage);
    }

    useEffect(() => {
        console.log(searchQuery)
        fetchData();
    }, [pageNumber, pageSize, searchQuery]);

    return {
        page,
        setPageNumber,
        searchQuery,
        setSearchQuery
    };
}