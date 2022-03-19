import React, { useState } from "react";
import {
  Container,
  Grid,
  Paper,
  TextField,
  IconButton,
  Collapse,
} from "@mui/material";
import { SubmitHandler, useForm } from "react-hook-form";
import {
  FolderOpen as FolderIcon,
  FilterList as FilterListIcon,
  Search as SearchIcon,
} from "@mui/icons-material";
import cx from "classnames";
import styles from "./Search.module.scss";

type FormData = {
  searchQuery: string;
  rootDirectory: string;
};

export const Search = () => {
  const [filtersExpanded, setFiltersExpanded] = useState(false);

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<FormData>();

  const onSubmit: SubmitHandler<FormData> = (data) => {
    console.log(data);
  };

  return (
    <Container>
      <h2>Ultimate Search Engine</h2>
      <form onSubmit={handleSubmit(onSubmit)}>
        <Paper
          elevation={8}
          sx={{ p: 2 }}
          className={cx({
            [styles.borderRadiusTopOnly]: filtersExpanded,
          })}
        >
          <Grid container spacing={2}>
            <Grid item xs={6}>
              <TextField
                id="search-query"
                label="Search query"
                variant="outlined"
                fullWidth
                error={!!errors.searchQuery}
                helperText={errors.searchQuery?.message ?? "Phrase in Polish"}
                {...register("searchQuery", {
                  required: "Field must not be empty",
                })}
              />
            </Grid>
            <Grid item xs={4}>
              <TextField
                id="root-catalog"
                label="Root catalog"
                variant="outlined"
                fullWidth
                error={!!errors.rootDirectory}
                helperText={
                  errors.rootDirectory?.message ?? 'E.g. "/home/user/"'
                }
                InputProps={{
                  endAdornment: <FolderIcon />,
                }}
                {...register("rootDirectory", {
                  required: "Field must not be empty",
                })}
              />
            </Grid>
            <Grid
              item
              xs={1}
              display="flex"
              justifyContent="center"
              alignItems="center"
            >
              <IconButton
                size="large"
                onClick={() => setFiltersExpanded(!filtersExpanded)}
              >
                <FilterListIcon fontSize="inherit" />
              </IconButton>
            </Grid>
            <Grid
              item
              xs={1}
              display="flex"
              justifyContent="center"
              alignItems="center"
            >
              <IconButton size="large" type="submit">
                <SearchIcon fontSize="inherit" />
              </IconButton>
            </Grid>
          </Grid>
        </Paper>
        <Collapse in={filtersExpanded}>
          <Paper
            elevation={12}
            sx={{ p: 2 }}
            className={cx({
              [styles.borderRadiusBottomOnly]: filtersExpanded,
            })}
          >
            <Grid container spacing={2}>
              <Grid item xs={12}>
                Advanced filters coming soon
              </Grid>
            </Grid>
          </Paper>
        </Collapse>
      </form>
    </Container>
  );
};
