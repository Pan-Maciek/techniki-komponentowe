import React, { useState } from "react";
import { Collapse, Grid, IconButton, Paper, TextField } from "@mui/material";
import cx from "classnames";
import styles from "./Search.module.scss";
import {
  FilterList as FilterListIcon,
  FolderOpen as FolderIcon,
  Search as SearchIcon,
} from "@mui/icons-material";
import { SubmitHandler, useForm } from "react-hook-form";
import { SearchParams } from "../../commons/types";

type SearchFormProps = {
  onSubmit: SubmitHandler<SearchParams>;
};

export const SearchForm: React.VFC<SearchFormProps> = ({ onSubmit }) => {
  const [filtersExpanded, setFiltersExpanded] = useState<boolean>(false);
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<SearchParams>();

  return (
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
              error={!!errors.phrase}
              helperText={errors.phrase?.message ?? "Phrase in Polish"}
              {...register("phrase", {
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
              error={!!errors.rootPath}
              helperText={errors.rootPath?.message ?? 'E.g. "/home/user/"'}
              InputProps={{
                endAdornment: <FolderIcon />,
              }}
              {...register("rootPath", {
                required: "Field must not be empty",
              })}
            />
          </Grid>
          <Grid
            item
            xs={1}
            display="flex"
            justifyContent="center"
            alignItems="flex-start"
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
            alignItems="flex-start"
          >
            <IconButton size="large" type="submit">
              <SearchIcon fontSize="inherit" />
            </IconButton>
          </Grid>
        </Grid>
      </Paper>
      <Collapse in={filtersExpanded}>
        <Paper
          elevation={4}
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
  );
};
