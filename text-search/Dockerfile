FROM mcr.microsoft.com/dotnet/aspnet:6.0 AS base
WORKDIR /app
EXPOSE 80
EXPOSE 443

FROM mcr.microsoft.com/dotnet/sdk:6.0 AS build
WORKDIR /src
COPY ["text-search.csproj", "./"]
RUN dotnet restore "text-search.csproj"
COPY . .
WORKDIR "/src/"
RUN dotnet build "text-search.csproj" -c Release -o /app/build

FROM build AS publish
RUN dotnet publish "text-search.csproj" -c Release -o /app/publish

FROM base AS final
WORKDIR /app
COPY --from=publish /app/publish .
ENTRYPOINT ["dotnet", "text-search.dll"]
