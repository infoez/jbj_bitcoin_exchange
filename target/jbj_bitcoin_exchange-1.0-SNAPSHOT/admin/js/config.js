/**
 * INSPINIA - Responsive Admin Theme
 *
 * Inspinia theme use AngularUI Router to manage routing and views
 * Each view are defined as state.
 * Initial there are written state for all view in theme.
 *
 */
function config($stateProvider, $urlRouterProvider, $ocLazyLoadProvider, IdleProvider, KeepaliveProvider) {

    // Configure Idle settings
    IdleProvider.idle(5); // in seconds
    IdleProvider.timeout(120); // in seconds

    $urlRouterProvider.otherwise("/dashboards/dashboard_1");

    $ocLazyLoadProvider.config({
        // Set to true if you want to see what and when is dynamically loaded
        debug: false
    });

    $stateProvider
        .state('dashboards', {
            abstract: true,
            url: "/dashboards",
            templateUrl: "client/views/common/content.html",
        })
        .state('dashboards.dashboard_1', {
            url: "/dashboard_1",
            templateUrl: "client/views/dashboard_1.html",
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {

                            serie: true,
                            name: 'angular-flot',
                            files: [ 'client/js/plugins/flot/jquery.flot.js', 'client/js/plugins/flot/jquery.flot.time.js', 'client/js/plugins/flot/jquery.flot.tooltip.min.js', 'client/js/plugins/flot/jquery.flot.spline.js', 'client/js/plugins/flot/jquery.flot.resize.js', 'client/js/plugins/flot/jquery.flot.pie.js', 'client/js/plugins/flot/curvedLines.js', 'client/js/plugins/flot/angular-flot.js', ]
                        },
                        {
                            name: 'angles',
                            files: ['client/js/plugins/chartJs/angles.js', 'client/js/plugins/chartJs/Chart.min.js']
                        },
                        {
                            name: 'angular-peity',
                            files: ['client/js/plugins/peity/jquery.peity.min.js', 'client/js/plugins/peity/angular-peity.js']
                        }
                    ]);
                }
            }
        })
        .state('dashboards.dashboard_2', {
            url: "/dashboard_2",
            templateUrl: "client/views/dashboard_2.html",
            data: { pageTitle: 'Dashboard 2' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            serie: true,
                            name: 'angular-flot',
                            files: [ 'client/js/plugins/flot/jquery.flot.js', 'client/js/plugins/flot/jquery.flot.time.js', 'client/js/plugins/flot/jquery.flot.tooltip.min.js', 'client/js/plugins/flot/jquery.flot.spline.js', 'client/js/plugins/flot/jquery.flot.resize.js', 'client/js/plugins/flot/jquery.flot.pie.js', 'client/js/plugins/flot/curvedLines.js', 'client/js/plugins/flot/angular-flot.js' ]
                        },
                        {
                            serie: true,
                            files: ['client/js/plugins/jvectormap/jquery-jvectormap-2.0.2.min.js', 'client/js/plugins/jvectormap/jquery-jvectormap-2.0.2.css']
                        },
                        {
                            serie: true,
                            files: ['client/js/plugins/jvectormap/jquery-jvectormap-world-mill-en.js']
                        },
                        {
                            name: 'ui.checkbox',
                            files: ['client/js/bootstrap/angular-bootstrap-checkbox.js']
                        }
                    ]);
                }
            }
        })
        .state('dashboards.dashboard_3', {
            url: "/dashboard_3",
            templateUrl: "client/views/dashboard_3.html",
            data: { pageTitle: 'Dashboard 3' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            name: 'angles',
                            files: ['client/js/plugins/chartJs/angles.js', 'client/js/plugins/chartJs/Chart.min.js']
                        },
                        {
                            name: 'angular-peity',
                            files: ['client/js/plugins/peity/jquery.peity.min.js', 'client/js/plugins/peity/angular-peity.js']
                        },
                        {
                            name: 'ui.checkbox',
                            files: ['client/js/bootstrap/angular-bootstrap-checkbox.js']
                        }
                    ]);
                }
            }
        })
        .state('dashboards_top', {
            abstract: true,
            url: "/dashboards_top",
            templateUrl: "client/views/common/content_top_navigation.html",
        })
        .state('dashboards_top.dashboard_4', {
            url: "/dashboard_4",
            templateUrl: "client/views/dashboard_4.html",
            data: { pageTitle: 'Dashboard 4' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            name: 'angles',
                            files: ['client/js/plugins/chartJs/angles.js', 'client/js/plugins/chartJs/Chart.min.js']
                        },
                        {
                            name: 'angular-peity',
                            files: ['client/js/plugins/peity/jquery.peity.min.js', 'client/js/plugins/peity/angular-peity.js']
                        },
                        {
                            serie: true,
                            name: 'angular-flot',
                            files: [ 'client/js/plugins/flot/jquery.flot.js', 'client/js/plugins/flot/jquery.flot.time.js', 'client/js/plugins/flot/jquery.flot.tooltip.min.js', 'client/js/plugins/flot/jquery.flot.spline.js', 'client/js/plugins/flot/jquery.flot.resize.js', 'client/js/plugins/flot/jquery.flot.pie.js', 'client/js/plugins/flot/curvedLines.js', 'client/js/plugins/flot/angular-flot.js', ]
                        }
                    ]);
                }
            }
        })
        .state('dashboards.dashboard_4_1', {
            url: "/dashboard_4_1",
            templateUrl: "client/views/dashboard_4_1.html",
            data: { pageTitle: 'Dashboard 4' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            name: 'angles',
                            files: ['client/js/plugins/chartJs/angles.js', 'client/js/plugins/chartJs/Chart.min.js']
                        },
                        {
                            name: 'angular-peity',
                            files: ['client/js/plugins/peity/jquery.peity.min.js', 'client/js/plugins/peity/angular-peity.js']
                        },
                        {
                            serie: true,
                            name: 'angular-flot',
                            files: [ 'client/js/plugins/flot/jquery.flot.js', 'client/js/plugins/flot/jquery.flot.time.js', 'client/js/plugins/flot/jquery.flot.tooltip.min.js', 'client/js/plugins/flot/jquery.flot.spline.js', 'client/js/plugins/flot/jquery.flot.resize.js', 'client/js/plugins/flot/jquery.flot.pie.js', 'client/js/plugins/flot/curvedLines.js', 'client/js/plugins/flot/angular-flot.js', ]
                        }
                    ]);
                }
            }
        })
        .state('dashboards.dashboard_5', {
            url: "/dashboard_5",
            templateUrl: "client/views/dashboard_5.html",
            data: { pageTitle: 'Dashboard 5' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            serie: true,
                            name: 'angular-flot',
                            files: [ 'client/js/plugins/flot/jquery.flot.js', 'client/js/plugins/flot/jquery.flot.time.js', 'client/js/plugins/flot/jquery.flot.tooltip.min.js', 'client/js/plugins/flot/jquery.flot.spline.js', 'client/js/plugins/flot/jquery.flot.resize.js', 'client/js/plugins/flot/jquery.flot.pie.js', 'client/js/plugins/flot/curvedLines.js', 'client/js/plugins/flot/angular-flot.js', ]
                        },
                        {
                            files: ['client/js/plugins/sparkline/jquery.sparkline.min.js']
                        }
                    ]);
                }
            }
        })
        .state('layouts', {
            url: "/layouts",
            templateUrl: "client/views/layouts.html",
            data: { pageTitle: 'Layouts' },
        })
        .state('charts', {
            abstract: true,
            url: "/charts",
            templateUrl: "client/views/common/content.html",
        })
        .state('charts.flot_chart', {
            url: "/flot_chart",
            templateUrl: "client/views/graph_flot.html",
            data: { pageTitle: 'Flot chart' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            serie: true,
                            name: 'angular-flot',
                            files: [ 'client/js/plugins/flot/jquery.flot.js', 'client/js/plugins/flot/jquery.flot.time.js', 'client/js/plugins/flot/jquery.flot.tooltip.min.js', 'client/js/plugins/flot/jquery.flot.spline.js', 'client/js/plugins/flot/jquery.flot.resize.js', 'client/js/plugins/flot/jquery.flot.pie.js', 'client/js/plugins/flot/curvedLines.js', 'client/js/plugins/flot/angular-flot.js', ]
                        }
                    ]);
                }
            }
        })
        .state('charts.rickshaw_chart', {
            url: "/rickshaw_chart",
            templateUrl: "client/views/graph_rickshaw.html",
            data: { pageTitle: 'Rickshaw chart' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            reconfig: true,
                            serie: true,
                            files: ['client/js/plugins/rickshaw/vendor/d3.v3.js','client/js/plugins/rickshaw/rickshaw.min.js']
                        },
                        {
                            reconfig: true,
                            name: 'angular-rickshaw',
                            files: ['client/js/plugins/rickshaw/angular-rickshaw.js']
                        }
                    ]);
                }
            }
        })
        .state('charts.peity_chart', {
            url: "/peity_chart",
            templateUrl: "client/views/graph_peity.html",
            data: { pageTitle: 'Peity graphs' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            name: 'angular-peity',
                            files: ['client/js/plugins/peity/jquery.peity.min.js', 'client/js/plugins/peity/angular-peity.js']
                        }
                    ]);
                }
            }
        })
        .state('charts.sparkline_chart', {
            url: "/sparkline_chart",
            templateUrl: "client/views/graph_sparkline.html",
            data: { pageTitle: 'Sparkline chart' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            files: ['client/js/plugins/sparkline/jquery.sparkline.min.js']
                        }
                    ]);
                }
            }
        })
        .state('charts.chartjs_chart', {
            url: "/chartjs_chart",
            templateUrl: "client/views/chartjs.html",
            data: { pageTitle: 'Chart.js' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            files: ['client/js/plugins/chartJs/Chart.min.js']
                        },
                        {
                            name: 'angles',
                            files: ['client/js/plugins/chartJs/angles.js']
                        }
                    ]);
                }
            }
        })
        .state('charts.chartist_chart', {
            url: "/chartist_chart",
            templateUrl: "client/views/chartist.html",
            data: { pageTitle: 'Chartist' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            serie: true,
                            name: 'angular-chartist',
                            files: ['client/js/plugins/chartist/chartist.min.js', 'css/plugins/chartist/chartist.min.css', 'client/js/plugins/chartist/angular-chartist.min.js']
                        }
                    ]);
                }
            }
        })
        .state('charts.c3charts', {
            url: "/c3charts",
            templateUrl: "client/views/c3charts.html",
            data: { pageTitle: 'c3charts' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            serie: true,
                            files: ['css/plugins/c3/c3.min.css', 'client/js/plugins/d3/d3.min.js', 'client/js/plugins/c3/c3.min.js']
                        },
                        {
                            serie: true,
                            name: 'gridshore.c3js.chart',
                            files: ['client/js/plugins/c3/c3-angular.min.js']
                        }
                    ]);
                }
            }
        })
        .state('mailbox', {
            abstract: true,
            url: "/mailbox",
            templateUrl: "client/views/common/content.html",
        })
        .state('mailbox.inbox', {
            url: "/inbox",
            templateUrl: "client/views/mailbox.html",
            data: { pageTitle: 'Mail Inbox' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            files: ['css/plugins/iCheck/custom.css','client/js/plugins/iCheck/icheck.min.js']
                        }
                    ]);
                }
            }
        })
        .state('mailbox.email_view', {
            url: "/email_view",
            templateUrl: "client/views/mail_detail.html",
            data: { pageTitle: 'Mail detail' }
        })
        .state('mailbox.email_compose', {
            url: "/email_compose",
            templateUrl: "client/views/mail_compose.html",
            data: { pageTitle: 'Mail compose' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            files: ['css/plugins/summernote/summernote.css','css/plugins/summernote/summernote-bs3.css','client/js/plugins/summernote/summernote.min.js']
                        },
                        {
                            name: 'summernote',
                            files: ['css/plugins/summernote/summernote.css','css/plugins/summernote/summernote-bs3.css','client/js/plugins/summernote/summernote.min.js','client/js/plugins/summernote/angular-summernote.min.js']
                        }
                    ]);
                }
            }
        })
        .state('mailbox.email_template', {
            url: "/email_template",
            templateUrl: "client/views/email_template.html",
            data: { pageTitle: 'Mail compose' }
        })
        .state('widgets', {
            url: "/widgets",
            templateUrl: "client/views/widgets.html",
            data: { pageTitle: 'Widhets' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            serie: true,
                            name: 'angular-flot',
                            files: [ 'client/js/plugins/flot/jquery.flot.js', 'client/js/plugins/flot/jquery.flot.time.js', 'client/js/plugins/flot/jquery.flot.tooltip.min.js', 'client/js/plugins/flot/jquery.flot.spline.js', 'client/js/plugins/flot/jquery.flot.resize.js', 'client/js/plugins/flot/jquery.flot.pie.js', 'client/js/plugins/flot/curvedLines.js', 'client/js/plugins/flot/angular-flot.js', ]
                        },
                        {
                            files: ['css/plugins/iCheck/custom.css','client/js/plugins/iCheck/icheck.min.js']
                        },
                        {
                            serie: true,
                            files: ['client/js/plugins/jvectormap/jquery-jvectormap-2.0.2.min.js', 'client/js/plugins/jvectormap/jquery-jvectormap-2.0.2.css']
                        },
                        {
                            serie: true,
                            files: ['client/js/plugins/jvectormap/jquery-jvectormap-world-mill-en.js']
                        },
                        {
                            name: 'ui.checkbox',
                            files: ['client/js/bootstrap/angular-bootstrap-checkbox.js']
                        }
                    ]);
                }
            }
        })
        .state('metrics', {
            url: "/metrics",
            templateUrl: "client/views/metrics.html",
            data: { pageTitle: 'Metrics' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            files: ['client/js/plugins/sparkline/jquery.sparkline.min.js']
                        }
                    ]);
                }
            }
        })
        .state('forms', {
            abstract: true,
            url: "/forms",
            templateUrl: "client/views/common/content.html",
        })
        .state('forms.basic_form', {
            url: "/basic_form",
            templateUrl: "client/views/form_basic.html",
            data: { pageTitle: 'Basic form' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            files: ['css/plugins/iCheck/custom.css','client/js/plugins/iCheck/icheck.min.js']
                        }
                    ]);
                }
            }
        })
        .state('forms.advanced_plugins', {
            url: "/advanced_plugins",
            templateUrl: "client/views/form_advanced.html",
            data: { pageTitle: 'Advanced form' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            files: ['client/js/plugins/moment/moment.min.js']
                        },
                        {
                            name: 'ui.knob',
                            files: ['client/js/plugins/jsKnob/jquery.knob.js','client/js/plugins/jsKnob/angular-knob.js']
                        },
                        {
                            files: ['css/plugins/ionRangeSlider/ion.rangeSlider.css','css/plugins/ionRangeSlider/ion.rangeSlider.skinFlat.css','client/js/plugins/ionRangeSlider/ion.rangeSlider.min.js']
                        },
                        {
                            insertBefore: '#loadBefore',
                            name: 'localytics.directives',
                            files: ['css/plugins/chosen/bootstrap-chosen.css','client/js/plugins/chosen/chosen.jquery.js','client/js/plugins/chosen/chosen.js']
                        },
                        {
                            name: 'nouislider',
                            files: ['css/plugins/nouslider/jquery.nouislider.css','client/js/plugins/nouslider/jquery.nouislider.min.js','client/js/plugins/nouslider/angular-nouislider.js']
                        },
                        {
                            name: 'datePicker',
                            files: ['css/plugins/datapicker/angular-datapicker.css','client/js/plugins/datapicker/angular-datepicker.js']
                        },
                        {
                            files: ['client/js/plugins/jasny/jasny-bootstrap.min.js']
                        },
                        {
                            files: ['css/plugins/clockpicker/clockpicker.css', 'client/js/plugins/clockpicker/clockpicker.js']
                        },
                        {
                            name: 'ui.switchery',
                            files: ['css/plugins/switchery/switchery.css','client/js/plugins/switchery/switchery.js','client/js/plugins/switchery/ng-switchery.js']
                        },
                        {
                            name: 'colorpicker.module',
                            files: ['css/plugins/colorpicker/colorpicker.css','client/js/plugins/colorpicker/bootstrap-colorpicker-module.js']
                        },
                        {
                            name: 'ngImgCrop',
                            files: ['client/js/plugins/ngImgCrop/ng-img-crop.js','css/plugins/ngImgCrop/ng-img-crop.css']
                        },
                        {
                            serie: true,
                            files: ['client/js/plugins/daterangepicker/daterangepicker.js', 'css/plugins/daterangepicker/daterangepicker-bs3.css']
                        },
                        {
                            name: 'daterangepicker',
                            files: ['client/js/plugins/daterangepicker/angular-daterangepicker.js']
                        },
                        {
                            files: ['css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css']
                        },
                        {
                            name: 'ui.select',
                            files: ['client/js/plugins/ui-select/select.min.js', 'css/plugins/ui-select/select.min.css']
                        },
                        {
                            files: ['css/plugins/touchspin/jquery.bootstrap-touchspin.min.css', 'client/js/plugins/touchspin/jquery.bootstrap-touchspin.min.js']
                        },
                        {
                            name: 'ngTagsInput',
                            files: ['client/js/plugins/ngTags//ng-tags-input.min.js', 'css/plugins/ngTags/ng-tags-input-custom.min.css']
                        },
                        {
                            files: ['client/js/plugins/dualListbox/jquery.bootstrap-duallistbox.js','css/plugins/dualListbox/bootstrap-duallistbox.min.css']
                        },
                        {
                            name: 'frapontillo.bootstrap-duallistbox',
                            files: ['client/js/plugins/dualListbox/angular-bootstrap-duallistbox.js']
                        }

                    ]);
                }
            }
        })
        .state('forms.wizard', {
            url: "/wizard",
            templateUrl: "client/views/form_wizard.html",
            controller: wizardCtrl,
            data: { pageTitle: 'Wizard form' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            files: ['css/plugins/steps/jquery.steps.css']
                        }
                    ]);
                }
            }
        })
        .state('forms.wizard.step_one', {
            url: '/step_one',
            templateUrl: 'client/views/wizard/step_one.html',
            data: { pageTitle: 'Wizard form' }
        })
        .state('forms.wizard.step_two', {
            url: '/step_two',
            templateUrl: 'client/views/wizard/step_two.html',
            data: { pageTitle: 'Wizard form' }
        })
        .state('forms.wizard.step_three', {
            url: '/step_three',
            templateUrl: 'client/views/wizard/step_three.html',
            data: { pageTitle: 'Wizard form' }
        })
        .state('forms.file_upload', {
            url: "/file_upload",
            templateUrl: "client/views/form_file_upload.html",
            data: { pageTitle: 'File upload' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            files: ['css/plugins/dropzone/basic.css','css/plugins/dropzone/dropzone.css','client/js/plugins/dropzone/dropzone.js']
                        },
                        {
                            files: ['client/js/plugins/jasny/jasny-bootstrap.min.js', 'css/plugins/jasny/jasny-bootstrap.min.css' ]
                        }
                    ]);
                }
            }
        })
        .state('forms.text_editor', {
            url: "/text_editor",
            templateUrl: "client/views/form_editors.html",
            data: { pageTitle: 'Text editor' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            name: 'summernote',
                            files: ['css/plugins/summernote/summernote.css','css/plugins/summernote/summernote-bs3.css','client/js/plugins/summernote/summernote.min.js','client/js/plugins/summernote/angular-summernote.min.js']
                        }
                    ]);
                }
            }
        })
        .state('forms.autocomplete', {
            url: "/autocomplete",
            templateUrl: "client/views/autocomplete.html",
            data: { pageTitle: 'Autocomplete' }

        })
        .state('forms.markdown', {
            url: "/markdown",
            templateUrl: "client/views/markdown.html",
            data: { pageTitle: 'Markdown' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            serie: true,
                            files: ['client/js/plugins/bootstrap-markdown/bootstrap-markdown.js','client/js/plugins/bootstrap-markdown/markdown.js','css/plugins/bootstrap-markdown/bootstrap-markdown.min.css']
                        }
                    ]);
                }
            }
        })
        .state('app', {
            abstract: true,
            url: "/app",
            templateUrl: "client/views/common/content.html",
        })
        .state('app.contacts', {
            url: "/contacts",
            templateUrl: "client/views/contacts.html",
            data: { pageTitle: 'Contacts' }
        })
        .state('app.contacts_2', {
            url: "/contacts_2",
            templateUrl: "client/views/contacts_2.html",
            data: { pageTitle: 'Contacts 2' }
        })
        .state('app.profile', {
            url: "/profile",
            templateUrl: "client/views/profile.html",
            data: { pageTitle: 'Profile' }
        })
        .state('app.profile_2', {
            url: "/profile_2",
            templateUrl: "client/views/profile_2.html",
            data: { pageTitle: 'Profile_2'},
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            files: ['client/js/plugins/sparkline/jquery.sparkline.min.js']
                        }
                    ]);
                }
            }
        })
        .state('app.projects', {
            url: "/projects",
            templateUrl: "client/views/projects.html",
            data: { pageTitle: 'Projects' }
        })
        .state('app.project_detail', {
            url: "/project_detail",
            templateUrl: "client/views/project_detail.html",
            data: { pageTitle: 'Project detail' }
        })
        .state('app.activity_stream', {
            url: "/activity_stream",
            templateUrl: "client/views/activity_stream.html",
            data: { pageTitle: 'Activity stream' }
        })
        .state('app.file_manager', {
            url: "/file_manager",
            templateUrl: "client/views/file_manager.html",
            data: { pageTitle: 'File manager' }
        })
        .state('app.calendar', {
            url: "/calendar",
            templateUrl: "client/views/calendar.html",
            data: { pageTitle: 'Calendar' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            insertBefore: '#loadBefore',
                            files: ['css/plugins/fullcalendar/fullcalendar.css','client/js/plugins/fullcalendar/fullcalendar.min.js','client/js/plugins/fullcalendar/gcal.js']
                        },
                        {
                            name: 'ui.calendar',
                            files: ['client/js/plugins/fullcalendar/calendar.js']
                        }
                    ]);
                }
            }
        })
        .state('app.faq', {
            url: "/faq",
            templateUrl: "client/views/faq.html",
            data: { pageTitle: 'FAQ' }
        })
        .state('app.timeline', {
            url: "/timeline",
            templateUrl: "client/views/timeline.html",
            data: { pageTitle: 'Timeline' }
        })
        .state('app.pin_board', {
            url: "/pin_board",
            templateUrl: "client/views/pin_board.html",
            data: { pageTitle: 'Pin board' }
        })
        .state('app.invoice', {
            url: "/invoice",
            templateUrl: "client/views/invoice.html",
            data: { pageTitle: 'Invoice' }
        })
        .state('app.blog', {
            url: "/blog",
            templateUrl: "client/views/blog.html",
            data: { pageTitle: 'Blog' }
        })
        .state('app.article', {
            url: "/article",
            templateUrl: "client/views/article.html",
            data: { pageTitle: 'Article' }
        })
        .state('app.issue_tracker', {
            url: "/issue_tracker",
            templateUrl: "client/views/issue_tracker.html",
            data: { pageTitle: 'Issue Tracker' }
        })
        .state('app.clients', {
            url: "/clients",
            templateUrl: "client/views/clients.html",
            data: { pageTitle: 'Clients' }
        })
        .state('app.teams_board', {
            url: "/teams_board",
            templateUrl: "client/views/teams_board.html",
            data: { pageTitle: 'Teams board' }
        })
        .state('app.social_feed', {
            url: "/social_feed",
            templateUrl: "client/views/social_feed.html",
            data: { pageTitle: 'Social feed' }
        })
        .state('app.vote_list', {
            url: "/vote_list",
            templateUrl: "client/views/vote_list.html",
            data: { pageTitle: 'Vote list' }
        })
        .state('pages', {
            abstract: true,
            url: "/pages",
            templateUrl: "client/views/common/content.html"
        })
        .state('pages.search_results', {
            url: "/search_results",
            templateUrl: "client/views/search_results.html",
            data: { pageTitle: 'Search results' }
        })
        .state('pages.empy_page', {
            url: "/empy_page",
            templateUrl: "client/views/empty_page.html",
            data: { pageTitle: 'Empty page' }
        })
        .state('logins', {
            url: "/logins",
            templateUrl: "client/views/login.html",
            data: { pageTitle: 'Login', specialClass: 'gray-bg' }
        })
        .state('login_two_columns', {
            url: "/login_two_columns",
            templateUrl: "client/views/login_two_columns.html",
            data: { pageTitle: 'Login two columns', specialClass: 'gray-bg' }
        })
        .state('register', {
            url: "/register",
            templateUrl: "client/views/register.html",
            data: { pageTitle: 'Register', specialClass: 'gray-bg' }
        })
        .state('lockscreen', {
            url: "/lockscreen",
            templateUrl: "client/views/lockscreen.html",
            data: { pageTitle: 'Lockscreen', specialClass: 'gray-bg' }
        })
        .state('forgot_password', {
            url: "/forgot_password",
            templateUrl: "client/views/forgot_password.html",
            data: { pageTitle: 'Forgot password', specialClass: 'gray-bg' }
        })
        .state('errorOne', {
            url: "/errorOne",
            templateUrl: "client/views/errorOne.html",
            data: { pageTitle: '404', specialClass: 'gray-bg' }
        })
        .state('errorTwo', {
            url: "/errorTwo",
            templateUrl: "client/views/errorTwo.html",
            data: { pageTitle: '500', specialClass: 'gray-bg' }
        })
        .state('ui', {
            abstract: true,
            url: "/ui",
            templateUrl: "client/views/common/content.html",
        })
        .state('ui.typography', {
            url: "/typography",
            templateUrl: "client/views/typography.html",
            data: { pageTitle: 'Typography' }
        })
        .state('ui.icons', {
            url: "/icons",
            templateUrl: "client/views/icons.html",
            data: { pageTitle: 'Icons' }
        })
        .state('ui.buttons', {
            url: "/buttons",
            templateUrl: "client/views/buttons.html",
            data: { pageTitle: 'Buttons' }
        })
        .state('ui.tabs_panels', {
            url: "/tabs_panels",
            templateUrl: "client/views/tabs_panels.html",
            data: { pageTitle: 'Panels' }
        })
        .state('ui.tabs', {
            url: "/tabs",
            templateUrl: "client/views/tabs.html",
            data: { pageTitle: 'Tabs' }
        })
        .state('ui.notifications_tooltips', {
            url: "/notifications_tooltips",
            templateUrl: "client/views/notifications.html",
            data: { pageTitle: 'Notifications and tooltips' }
        })
        .state('ui.helper_classes', {
            url: "/helper_classes",
            templateUrl: "client/views/helper_classes.html",
            data: { pageTitle: 'Helper css classes' }
        })
        .state('ui.badges_labels', {
            url: "/badges_labels",
            templateUrl: "client/views/badges_labels.html",
            data: { pageTitle: 'Badges and labels and progress' }
        })
        .state('ui.video', {
            url: "/video",
            templateUrl: "client/views/video.html",
            data: { pageTitle: 'Responsible Video' }
        })
        .state('ui.draggable', {
            url: "/draggable",
            templateUrl: "client/views/draggable.html",
            data: { pageTitle: 'Draggable panels' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            name: 'ui.sortable',
                            files: ['client/js/plugins/ui-sortable/sortable.js']
                        }
                    ]);
                }
            }
        })
        .state('grid_optionss', {
            url: "/grid_options",
            templateUrl: "client/views/grid_options.html",
            data: { pageTitle: 'Grid options' }
        })
        .state('miscellaneous', {
            abstract: true,
            url: "/miscellaneous",
            templateUrl: "client/views/common/content.html",
        })
        .state('miscellaneous.google_maps', {
            url: "/google_maps",
            templateUrl: "client/views/google_maps.html",
            data: { pageTitle: 'Google maps' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            name: 'ui.event',
                            files: ['client/js/plugins/uievents/event.js']
                        },
                        {
                            name: 'ui.map',
                            files: ['client/js/plugins/uimaps/ui-map.js']
                        },
                    ]);
                }
            }
        })
        .state('miscellaneous.datamaps', {
            url: "/datamaps",
            templateUrl: "client/views/datamaps.html",
            data: { pageTitle: 'Datamaps' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            files: ['client/js/plugins/d3/d3.min.js','client/js/plugins/topojson/topojson.js','client/js/plugins/datamaps/datamaps.all.min.js']
                        },
                        {
                            name: 'datamaps',
                            files: ['client/js/plugins/angular-datamaps/angular-datamaps.min.js']
                        },
                    ]);
                }
            }
        })
        .state('miscellaneous.socialbuttons', {
            url: "/socialbuttons",
            templateUrl: "client/views/socialbuttons.html",
            data: { pageTitle: 'Social buttons' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            files: ['css/plugins/bootstrapSocial/bootstrap-social.css']
                        }
                    ]);
                }
            }
        })
        .state('miscellaneous.code_editor', {
            url: "/code_editor",
            templateUrl: "client/views/code_editor.html",
            data: { pageTitle: 'Code Editor' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            serie: true,
                            files: ['css/plugins/codemirror/codemirror.css','css/plugins/codemirror/ambiance.css','client/js/plugins/codemirror/codemirror.js','client/js/plugins/codemirror/mode/javascript/javascript.js']
                        },
                        {
                            name: 'ui.codemirror',
                            files: ['client/js/plugins/ui-codemirror/ui-codemirror.min.js']
                        }
                    ]);
                }
            }
        })
        .state('miscellaneous.modal_window', {
            url: "/modal_window",
            templateUrl: "client/views/modal_window.html",
            data: { pageTitle: 'Modal window' }
        })
        .state('miscellaneous.chat_view', {
            url: "/chat_view",
            templateUrl: "client/views/chat_view.html",
            data: { pageTitle: 'Chat view' }
        })
        .state('miscellaneous.nestable_list', {
            url: "/nestable_list",
            templateUrl: "client/views/nestable_list.html",
            data: { pageTitle: 'Nestable List' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            name: 'ui.tree',
                            files: ['css/plugins/uiTree/angular-ui-tree.min.css','client/js/plugins/uiTree/angular-ui-tree.min.js']
                        },
                    ]);
                }
            }
        })
        .state('miscellaneous.notify', {
            url: "/notify",
            templateUrl: "client/views/notify.html",
            data: { pageTitle: 'Notifications for angularJS' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            name: 'cgNotify',
                            files: ['css/plugins/angular-notify/angular-notify.min.css','client/js/plugins/angular-notify/angular-notify.min.js']
                        }
                    ]);
                }
            }
        })
        .state('miscellaneous.timeline_2', {
            url: "/timeline_2",
            templateUrl: "client/views/timeline_2.html",
            data: { pageTitle: 'Timeline version 2' }
        })
        .state('miscellaneous.forum_view', {
            url: "/forum_view",
            templateUrl: "client/views/forum_view.html",
            data: { pageTitle: 'Forum - general view' }
        })
        .state('miscellaneous.forum_post_view', {
            url: "/forum_post_view",
            templateUrl: "client/views/forum_post_view.html",
            data: { pageTitle: 'Forum - post view' }
        })
        .state('miscellaneous.diff', {
            url: "/diff",
            templateUrl: "client/views/diff.html",
            data: { pageTitle: 'Text Diff' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            files: ['client/js/plugins/diff_match_patch/javascript/diff_match_patch.js']
                        },
                        {
                            name: 'diff-match-patch',
                            files: ['client/js/plugins/angular-diff-match-patch/angular-diff-match-patch.js']
                        }
                    ]);
                }
            }
        })
        .state('miscellaneous.pdf_viewer', {
            url: "/pdf_viewer",
            templateUrl: "client/views/pdf_viewer.html",
            data: { pageTitle: 'PDF viewer' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            files: ['client/js/plugins/pdfjs/pdf.js']
                        },
                        {
                            name: 'pdf',
                            files: ['client/js/plugins/pdfjs/angular-pdf.js']
                        }
                    ]);
                }
            }
        })
        .state('miscellaneous.sweet_alert', {
            url: "/sweet_alert",
            templateUrl: "client/views/sweet_alert.html",
            data: { pageTitle: 'Sweet alert' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            files: ['client/js/plugins/sweetalert/sweetalert.min.js', 'css/plugins/sweetalert/sweetalert.css']
                        },
                        {
                            name: 'oitozero.ngSweetAlert',
                            files: ['client/js/plugins/sweetalert/angular-sweetalert.min.js']
                        }
                    ]);
                }
            }
        })
        .state('miscellaneous.idle_timer', {
            url: "/idle_timer",
            templateUrl: "client/views/idle_timer.html",
            data: { pageTitle: 'Idle timer' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            name: 'cgNotify',
                            files: ['css/plugins/angular-notify/angular-notify.min.css','client/js/plugins/angular-notify/angular-notify.min.js']
                        }
                    ]);
                }
            }
        })
        .state('miscellaneous.live_favicon', {
            url: "/live_favicon",
            templateUrl: "client/views/live_favicon.html",
            data: { pageTitle: 'Live favicon' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            files: ['client/js/plugins/tinycon/tinycon.min.js']
                        }
                    ]);
                }
            }
        })
        .state('miscellaneous.spinners', {
            url: "/spinners",
            templateUrl: "client/views/spinners.html",
            data: { pageTitle: 'Spinners' }
        })
        .state('miscellaneous.spinners_usage', {
            url: "/spinners_usage",
            templateUrl: "client/views/spinners_usage.html",
            data: { pageTitle: 'Spinners usage' }
        })
        .state('miscellaneous.validation', {
            url: "/validation",
            templateUrl: "client/views/validation.html",
            data: { pageTitle: 'Validation' }
        })
        .state('miscellaneous.agile_board', {
            url: "/agile_board",
            templateUrl: "client/views/agile_board.html",
            data: { pageTitle: 'Agile board' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            name: 'ui.sortable',
                            files: ['client/js/plugins/ui-sortable/sortable.js']
                        }
                    ]);
                }
            }
        })
        .state('miscellaneous.masonry', {
            url: "/masonry",
            templateUrl: "client/views/masonry.html",
            data: { pageTitle: 'Masonry' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            files: ['client/js/plugins/masonry/masonry.pkgd.min.js']
                        },
                        {
                            name: 'wu.masonry',
                            files: ['client/js/plugins/masonry/angular-masonry.min.js']
                        }
                    ]);
                }
            }
        })
        .state('miscellaneous.toastr', {
            url: "/toastr",
            templateUrl: "client/views/toastr.html",
            data: { pageTitle: 'Toastr' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            insertBefore: '#loadBefore',
                            name: 'toaster',
                            files: ['client/js/plugins/toastr/toastr.min.js', 'css/plugins/toastr/toastr.min.css']
                        }
                    ]);
                }
            }
        })
        .state('miscellaneous.i18support', {
            url: "/i18support",
            templateUrl: "client/views/i18support.html",
            data: { pageTitle: 'i18support' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            insertBefore: '#loadBefore',
                            name: 'toaster',
                            files: ['client/js/plugins/toastr/toastr.min.js', 'css/plugins/toastr/toastr.min.css']
                        }
                    ]);
                }
            }
        })
        .state('miscellaneous.truncate', {
            url: "/truncate",
            templateUrl: "client/views/truncate.html",
            data: { pageTitle: 'Truncate' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            files: ['client/js/plugins/dotdotdot/jquery.dotdotdot.min.js']
                        }
                    ]);
                }
            }
        })
        .state('miscellaneous.password_meter', {
            url: "/password_meter",
            templateUrl: "client/views/password_meter.html",
            data: { pageTitle: 'Password meter' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            files: ['client/js/plugins/pwstrength/pwstrength-bootstrap.min.js', 'client/js/plugins/pwstrength/zxcvbn.js']
                        }
                    ]);
                }
            }
        })
        .state('miscellaneous.clipboard', {
            url: "/clipboard",
            templateUrl: "client/views/clipboard.html",
            data: { pageTitle: 'Clipboard' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            files: ['client/js/plugins/ngclipboard/clipboard.min.js']
                        },
                        {
                            name: 'ngclipboard',
                            files: ['client/js/plugins/ngclipboard/ngclipboard.min.js']
                        }
                    ]);
                }
            }
        })
        .state('miscellaneous.text_spinners', {
            url: "/text_spinners",
            templateUrl: "client/views/text_spinners.html",
            data: { pageTitle: 'Text spinners' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            files: ['css/plugins/textSpinners/spinners.css']
                        }
                    ]);
                }
            }
        })
        .state('miscellaneous.loading_buttons', {
            url: "/loading_buttons",
            templateUrl: "client/views/loading_buttons.html",
            data: { pageTitle: 'Loading buttons' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            serie: true,
                            name: 'angular-ladda',
                            files: ['client/js/plugins/ladda/spin.min.js', 'client/js/plugins/ladda/ladda.min.js', 'css/plugins/ladda/ladda-themeless.min.css','client/js/plugins/ladda/angular-ladda.min.js']
                        }
                    ]);
                }
            }
        })
        .state('miscellaneous.tour', {
            url: "/tour",
            templateUrl: "client/views/tour.html",
            data: { pageTitle: 'Tour' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            insertBefore: '#loadBefore',
                            files: ['client/js/plugins/bootstrap-tour/bootstrap-tour.min.js', 'css/plugins/bootstrap-tour/bootstrap-tour.min.css']
                        },
                        {
                            name: 'bm.bsTour',
                            files: ['client/js/plugins/angular-bootstrap-tour/angular-bootstrap-tour.min.js']
                        }
                    ]);
                }
            }
        })
        .state('miscellaneous.tree_view', {
            url: "/tree_view",
            templateUrl: "client/views/tree_view.html",
            data: { pageTitle: 'Tree view' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            files: ['css/plugins/jsTree/style.min.css','client/js/plugins/jsTree/jstree.min.js']
                        },
                        {
                            name: 'ngJsTree',
                            files: ['client/js/plugins/jsTree/ngJsTree.min.js']
                        }
                    ]);
                }
            }
        })
        .state('tables', {
            abstract: true,
            url: "/tables",
            templateUrl: "client/views/common/content.html"
        })
        .state('tables.static_table', {
            url: "/static_table",
            templateUrl: "client/views/table_basic.html",
            data: { pageTitle: 'Static table' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            name: 'angular-peity',
                            files: ['client/js/plugins/peity/jquery.peity.min.js', 'client/js/plugins/peity/angular-peity.js']
                        },
                        {
                            files: ['css/plugins/iCheck/custom.css','client/js/plugins/iCheck/icheck.min.js']
                        }
                    ]);
                }
            }
        })
        .state('tables.data_tables', {
            url: "/data_tables",
            templateUrl: "client/views/table_data_tables.html",
            data: { pageTitle: 'Data Tables' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            serie: true,
                            files: ['client/js/plugins/dataTables/datatables.min.js','css/plugins/dataTables/datatables.min.css']
                        },
                        {
                            serie: true,
                            name: 'datatables',
                            files: ['client/js/plugins/dataTables/angular-datatables.min.js']
                        },
                        {
                            serie: true,
                            name: 'datatables.buttons',
                            files: ['client/js/plugins/dataTables/angular-datatables.buttons.min.js']
                        }
                    ]);
                }
            }
        })
        .state('tables.foo_table', {
            url: "/foo_table",
            templateUrl: "client/views/foo_table.html",
            data: { pageTitle: 'Foo Table' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            files: ['client/js/plugins/footable/footable.all.min.js', 'css/plugins/footable/footable.core.css']
                        },
                        {
                            name: 'ui.footable',
                            files: ['client/js/plugins/footable/angular-footable.js']
                        }
                    ]);
                }
            }
        })
        .state('tables.nggrid', {
            url: "/nggrid",
            templateUrl: "client/views/nggrid.html",
            data: { pageTitle: 'ng Grid' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            name: 'ngGrid',
                            files: ['client/js/plugins/nggrid/ng-grid-2.0.3.min.js']
                        },
                        {
                            insertBefore: '#loadBefore',
                            files: ['client/js/plugins/nggrid/ng-grid.css']
                        }
                    ]);
                }
            }
        })
        .state('commerce', {
            abstract: true,
            url: "/commerce",
            templateUrl: "client/views/common/content.html",
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            files: ['client/js/plugins/footable/footable.all.min.js', 'css/plugins/footable/footable.core.css']
                        },
                        {
                            name: 'ui.footable',
                            files: ['client/js/plugins/footable/angular-footable.js']
                        }
                    ]);
                }
            }
        })
        .state('commerce.products_grid', {
            url: "/products_grid",
            templateUrl: "client/views/ecommerce_products_grid.html",
            data: { pageTitle: 'E-commerce grid' }
        })
        .state('commerce.product_list', {
            url: "/product_list",
            templateUrl: "client/views/ecommerce_product_list.html",
            data: { pageTitle: 'E-commerce product list' }
        })
        .state('commerce.orders', {
            url: "/orders",
            templateUrl: "client/views/ecommerce_orders.html",
            data: { pageTitle: 'E-commerce orders' }
        })
        .state('commerce.product', {
            url: "/product",
            templateUrl: "client/views/ecommerce_product.html",
            data: { pageTitle: 'Product edit' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            files: ['css/plugins/summernote/summernote.css','css/plugins/summernote/summernote-bs3.css','client/js/plugins/summernote/summernote.min.js']
                        },
                        {
                            name: 'summernote',
                            files: ['css/plugins/summernote/summernote.css','css/plugins/summernote/summernote-bs3.css','client/js/plugins/summernote/summernote.min.js','client/js/plugins/summernote/angular-summernote.min.js']
                        }
                    ]);
                }
            }

        })
        .state('commerce.product_details', {
            url: "/product_details",
            templateUrl: "client/views/ecommerce_product_details.html",
            data: { pageTitle: 'E-commerce Product detail' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            files: ['css/plugins/slick/slick.css','css/plugins/slick/slick-theme.css','client/js/plugins/slick/slick.min.js']
                        },
                        {
                            name: 'slick',
                            files: ['client/js/plugins/slick/angular-slick.min.js']
                        }
                    ]);
                }
            }
        })
        .state('commerce.payments', {
            url: "/payments",
            templateUrl: "client/views/ecommerce_payments.html",
            data: { pageTitle: 'E-commerce payments' }
        })
        .state('commerce.cart', {
            url: "/cart",
            templateUrl: "client/views/ecommerce_cart.html",
            data: { pageTitle: 'Shopping cart' }
        })
        .state('gallery', {
            abstract: true,
            url: "/gallery",
            templateUrl: "client/views/common/content.html"
        })
        .state('gallery.basic_gallery', {
            url: "/basic_gallery",
            templateUrl: "client/views/basic_gallery.html",
            data: { pageTitle: 'Lightbox Gallery' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            files: ['client/js/plugins/blueimp/jquery.blueimp-gallery.min.js','css/plugins/blueimp/css/blueimp-gallery.min.css']
                        }
                    ]);
                }
            }
        })
        .state('gallery.bootstrap_carousel', {
            url: "/bootstrap_carousel",
            templateUrl: "client/views/carousel.html",
            data: { pageTitle: 'Bootstrap carousel' }
        })
        .state('gallery.slick_gallery', {
            url: "/slick_gallery",
            templateUrl: "client/views/slick.html",
            data: { pageTitle: 'Slick carousel' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            files: ['css/plugins/slick/slick.css','css/plugins/slick/slick-theme.css','client/js/plugins/slick/slick.min.js']
                        },
                        {
                            name: 'slick',
                            files: ['client/js/plugins/slick/angular-slick.min.js']
                        }
                    ]);
                }
            }
        })
        .state('css_animations', {
            url: "/css_animations",
            templateUrl: "client/views/css_animation.html",
            data: { pageTitle: 'CSS Animations' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            reconfig: true,
                            serie: true,
                            files: ['client/js/plugins/rickshaw/vendor/d3.v3.js','client/js/plugins/rickshaw/rickshaw.min.js']
                        },
                        {
                            reconfig: true,
                            name: 'angular-rickshaw',
                            files: ['client/js/plugins/rickshaw/angular-rickshaw.js']
                        }
                    ]);
                }
            }

        })
        .state('landing', {
            url: "/landing",
            templateUrl: "client/views/landing.html",
            data: { pageTitle: 'Landing page', specialClass: 'landing-page' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            files: ['client/js/plugins/wow/wow.min.js']
                        }
                    ]);
                }
            }
        })
        .state('outlook', {
            url: "/outlook",
            templateUrl: "client/views/outlook.html",
            data: { pageTitle: 'Outlook view', specialClass: 'fixed-sidebar' }
        })
        .state('off_canvas', {
            url: "/off_canvas",
            templateUrl: "client/views/off_canvas.html",
            data: { pageTitle: 'Off canvas menu', specialClass: 'canvas-menu' }
        });

}
angular
    .module('inspinia')
    .config(config)
    .run(function($rootScope, $state) {
        $rootScope.$state = $state;
    });
