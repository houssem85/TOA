# App Architecture

The TOA application follows a MVVM architecture pattern throughout the project. Below is the
architecture diagram for the Login Screen , though every screen in the application should follow the
same organisation.

For the most up to date
diagram [Miro](https://miro.com/app/board/o9J_ltxT_4k=/?invite_link_id=617869994956).

![Architecture Diagram](assets/TOAFlows.jpg)

This document is intended to clarify each of the components in the application and what their
responsibilities are.

## Repository

A repository is any component that is responsible for making data requests. This could be a remote
server, local preference, database, etc. It should not have any side effects such as calling a
different repository. It should not do any data manipulating (sorting , filtering , etc). The
repository should be responsible for mapping information from DTO (data transfer object) models to
domain data classes.

Repository should not responsible for any data manipulation of a response. For example ,if a
repository is requesting a list of users, but a screen only cares about users with a certain
property, the use case should be responsible for filtering the list accordingly.

## Use Case

The purpose of a use case is to perform any action that occurs on a screen. This could be fetching
or submitting data,filtering items ,etc.
Domain specific business like this deserves its own component in the application's architecture.

A repository is only responsible for requesting receiving data . A ViewModel is only responsible for taking the result of an actions,
and mapping it into a view state.The use case is responsible for whatever happens in between.

## ViewModel

This component is responsible for connecting the view with any relevant use cases.
The view model consumes ui events , triggers a necessary use case , and processes a use case response into a view state
Tha is exposed for the screen to render.

A viewModel may also have certain actions that triggered the moment ViewModel is created.

## View

The View component is solely responsible for being able to display data 
on the ui. It can consume a view state witch specifies what information should be rendered.
ANy UI events such as inputs , button clicks , gestures , etc will be passed to the ViewModel 
witch determines what action needs to happen. 

